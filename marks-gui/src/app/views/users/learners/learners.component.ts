import { Component, OnInit } from '@angular/core';
import {User} from "../user.model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {UserService} from "../user.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-learners',
  templateUrl: './learners.component.html',
})
export class LearnersComponent implements OnInit{

  users: User [];
  user: User;
  form: FormGroup;

  titles: string[];
  schoolClasses: string[];
  schoolGrades: string[];
  genders: string[];
  races: string[];
  provinces: string[];

  isCollapsed: boolean = false;
  iconCollapse: string = 'icon-arrow-up';
  nationality: String;

  constructor(private userService: UserService, private formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.titles = this.userService.getTitles();
    this.schoolClasses = this.userService.getClasses();
    this.schoolGrades = this.userService.getSchoolGrades()
    this.genders = this.userService.getGenders();
    this.races = this.userService.getRaces();
    this.provinces = this.userService.getProvinces();
    this.initialiseForm();
    this.getLearners();
  }

  initialiseForm() {
    this.form = this.formBuilder.group(
        {
          id: "",
          userNumber: "",
          role : "",
          title : "",
          initials : "",
          fullName : "",
          lastName : "",
          gender : "",
          race : "",
          otherNationality : "",
          birthDate : new Date(),
          nationality : "",
          idNumber : "",
          passportNumber : "",
          emailAddress : "",
          phoneNumber : "",
          houseNo : "",
          city : "",
          cityCode : "",
          province : "",
          active : true,
          streetNo : ""
        }
    )
  }

  getLearners() {
    this.userService.getUsersByRole("Learner").subscribe(
        data => { this.users = data},
        err => console.error(err),
        () => console.log('Done loading learners')
    );
  }

  createNewUser() {
    this.initialiseForm();
    this.toggleCollapse();
  }

  viewListUsers() {
    this.getLearners();
    this.toggleCollapse();
  }

  viewLearners(teacherId:string) {
    this.userService.getUserByUserId(teacherId).subscribe(
        data => {
          this.user = data;
          this.form.patchValue(this.user);
          this.toggleCollapse();
        },
        err => console.error(err),
        () => console.log('Done fetching teacher user')
    );
  }

  submitForm(data){
    let authObs: Observable<boolean>;
    data["role"] = "Learner";
    authObs = this.userService.createUser(data);

    authObs.subscribe(
        resData => {
          console.log("User has been created" + data);
          this.getLearners();
          this.toggleCollapse();
        },
        errorMessage => {
          console.log(errorMessage);
        }
    );
  }

  collapsed(event: any): void {
    // console.log(event);
  }

  expanded(event: any): void {
    // console.log(event);
  }

  toggleCollapse(): void {
    this.isCollapsed = !this.isCollapsed;
    this.iconCollapse = this.isCollapsed ? 'icon-arrow-down' : 'icon-arrow-up';
  }
}
