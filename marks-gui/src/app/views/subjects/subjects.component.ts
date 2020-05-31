import { Component, OnInit } from '@angular/core';
import {Subject} from "./models/subjects.model";
import {Assessment} from "./models/assessment.model";
import {SubjectService} from "./subject.service";
import { FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Observable} from "rxjs";

@Component({
  selector: 'app-subjects',
  templateUrl: './subjects.component.html',
})
export class SubjectsComponent implements OnInit {

  assessments: Assessment[];
  assessment: Assessment;
  subjects: Subject [];
  subject: Subject;
  form: FormGroup;
  assessmentForm: FormGroup;

  isCollapsed: boolean = false;
  private submitted: boolean = false;
  private assessmentSubmitted: boolean = false;
  private index: number;

  constructor(private subjectService: SubjectService, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.getSubjects();
    this.initialiseSubjectForm();
    this.initialiseAssessmentForm();
  }

  initialiseSubjectForm() {
    this.form = this.formBuilder.group(
        {
          id: "",
          code : this.formBuilder.control("", Validators.required),
          description : this.formBuilder.control("", Validators.required),
          information : this.formBuilder.control(""),
          active : true
        }

    );
  }

  initialiseAssessmentForm() {
    this.assessmentForm = this.formBuilder.group(
        {
            id: "",
            name : this.formBuilder.control("", Validators.required),
            date : this.formBuilder.control("", Validators.required),
            total : this.formBuilder.control("", [Validators.required,
                Validators.max(100), Validators.min(0), Validators.pattern("^[0-9]*$")]),
            active : true
        }

    );
  }

  createNewSubject() {
    this.initialiseSubjectForm();
    this.assessments = [];
    this.toggleCollapse();
  }

  viewSubjects () {
    this.getSubjects();
    this.toggleCollapse();
  }

  viewSubject(subjectId :string) {
    this.assessments = [];
    this.subjectService.getSubject(subjectId).subscribe(
        data => {
          this.subject = data;
          console.log(this.subject);
          this.form.patchValue(this.subject);
          this.prepareAssessment(subjectId);
          this.toggleCollapse();
        },
        err => console.error(err),
        () => console.log('Done fetching subject')
    );
  }

  viewSubjectAssessment(index :number) {
    this.index = index;
    this.assessment = this.assessments[index];
    this.assessmentForm.patchValue(this.assessment);
  }


  prepareAssessment(subjectId : string) {
      console.log("The ID" + subjectId);
      this.subjectService.getAssessmentBySubject(subjectId).subscribe(
          data => {
              this.assessments = data;
          },
          err => console.error(err),
          () => console.log('Done fetching assessments')
      );
  }


  getSubjects() {
    this.subjectService.getSubjects().subscribe(
        data => {
          this.subjects = data
        },
        err => console.error(err),
        () => console.log('Done loading subjects')
    );
  }

  get f() { return this.form.controls; }
  get g() { return this.assessmentForm.controls; }

  collapsed(event: any): void {
    // console.log(event);
  }

  expanded(event: any): void {
     //console.log(event);
  }

  toggleCollapse(): void {
    this.isCollapsed = !this.isCollapsed;
  }

  submitForm(data){
      this.submitted = false;

      if (!this.form.valid) {
          this.submitted = true;
          return;
      }
      let authObs: Observable<boolean>;
      authObs = this.subjectService.createSubject(data);

      authObs.subscribe(
          resData => {
              console.log("Subject has been created" + resData);
              this.viewSubjects();
          },
          errorMessage => {
              console.log(errorMessage);
          }
      );
  }

  submitAssessmentForm(data){
      this.assessmentSubmitted = false;

      if (!this.assessmentForm.valid) {
          this.assessmentSubmitted = true;
          return;
      }

      let authObs: Observable<boolean>;
      data['subjectId'] = this.subject.id;
      authObs = this.subjectService.createAssessment(data);

      authObs.subscribe(
          resData => {
              console.log("Assessment has been created" + resData);
              this.prepareAssessment(this.subject.id);
              this.assessment = null;
              this.assessmentForm.reset();
          },
          errorMessage => {
              console.log(errorMessage);
          }
      );
  }
}
