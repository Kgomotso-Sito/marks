import { Component, OnInit } from '@angular/core';
import {UserService} from "../../users/user.service";
import {User} from "../../users/user.model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {SubjectService} from "../subject.service";
import {Subject} from "../models/subjects.model";
import {Observable} from "rxjs";

@Component({
  selector: 'app-enrollment',
  templateUrl: './enrollment.component.html',
})
export class EnrollmentComponent implements OnInit {
  users: User [];
  user: User;
  enrolled: Subject [];
  allSubjects: Subject [];

  isCollapsed: boolean = false;
  private submitted: boolean = false;
  private submittedSubject: boolean = false;
  private index: number;

  form: FormGroup;
  enrollment: FormGroup;
  subject: null;


  constructor(private userService: UserService, private subjectService: SubjectService, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.getLearners();
    this.initialiseForm();
    this.initialiseEnrollmentForm();
  }

  getLearners() {
    this.userService.getUsersByRole("Learner").subscribe(
        data => { this.users = data},
        err => console.error(err),
        () => console.log('Done loading learners')
    );
  }

  viewEnrollment(index :number) {
    this.index = index;
    this.user = this.users[index];
    this.getSubjects(this.user.id);
    this.form.patchValue(this.user);
    this.toggleCollapse();
    this.initialiseEnrollmentForm();
  }

  initialiseForm() {
    this.form = this.formBuilder.group(
        {
          userNumber : this.formBuilder.control(""),
          fullName : this.formBuilder.control(""),
          lastName : this.formBuilder.control("")
        }
    );
  }

  initialiseEnrollmentForm() {
    this.enrollment = this.formBuilder.group(
        {
          subject : this.formBuilder.control("" , Validators.required)
        }

    );
  }

  get f() { return this.enrollment.controls; }

  getSubjects(userId : string) {
    this.subjectService.getUserSubjects(userId).subscribe(
        data => {
          this.enrolled = data;
          this.getAllSubjects();
          console.log('enrolled subjects', data);
        },
        err => console.error(err),
        () => console.log('Done enrolled subjects')
    );
  }

  getAllSubjects() {
    this.subjectService.getSubjects().subscribe(
        data => {
          this.allSubjects = data;
          this.enrolled.forEach( enrolled => {
            this.allSubjects = this.allSubjects.filter(subject => subject.id !== enrolled.id);
          });
        },
        err => console.error(err),
        () => console.log('Done loading subjects available to enroll')
    );
  }

  collapsed(event: any): void {
    // console.log(event);
  }

  expanded(event: any): void {
    //console.log(event);
  }

  submitForm(data) {
    this.submitted = false;

    this.toggleCollapse();

    if (!this.form.valid) {
      this.submitted = true;
      return;
    }
  }

  deregister(index :number) {
    this.index = index;
    let enrollment: UserSubjectId = new UserSubjectId();
    enrollment.subjectId = this.enrolled[index].id;
    enrollment.userId = this.user.id;

    this.deregisterSubject(enrollment);
    this.toggleCollapse();

  }

  deregisterSubject(userSubjectId : UserSubjectId) {
    this.subjectService.deregisterSubject(userSubjectId).subscribe(
        data => {
          this.getSubjects(this.user.id);
          console.log('enrolled subjects', data);
        },
        err => console.error(err),
        () => console.log('Done de-registering subject')
    );
  }

  submitEnrollForm(data) {
    this.submittedSubject = false;

    if (!this.enrollment.valid) {
      this.submittedSubject = true;
      return;
    }

    let enrollment: UserSubjectId = new UserSubjectId();
    let subject: Subject = data.subject;
    enrollment.subjectId = subject.id;
    enrollment.userId = this.user.id;

    this.subjectService.enrollForSubject(enrollment);
    let authObs: Observable<boolean>;

    authObs = this.subjectService.enrollForSubject(enrollment);

    authObs.subscribe(
        resData => {
          console.log("User has enrolled" + resData);
          this.subject = null;
          this.enrollment.reset();
          this.initialiseEnrollmentForm();
          this.getSubjects(this.user.id);
        },
        errorMessage => {
          console.log(errorMessage);
        }
    );
  }

  toggleCollapse(): void {
    this.isCollapsed = !this.isCollapsed;
  }

}
