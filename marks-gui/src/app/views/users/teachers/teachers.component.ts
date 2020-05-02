import { Component, OnInit } from '@angular/core';
import {UserService} from "../user.service";
import {FormBuilder, FormGroup} from "@angular/forms";
import {User} from "../user.model";
import {Observable} from "rxjs";

@Component({
  selector: 'app-teachers',
  templateUrl: './teachers.component.html'
})
export class TeachersComponent implements OnInit{

  users: User [];
  user: User;
  form: FormGroup;

  titles: string[];
  genders: string[];
  races: string[];
  provinces: string[];

  constructor(private userService: UserService, private formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.titles = this.userService.getTitles();
    this.genders = this.userService.getGenders();
    this.races = this.userService.getRaces();
    this.provinces = this.userService.getProvinces();
    this.initialiseForm();
    this.getTeacherUsers();
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

  getTeacherUsers() {
    this.userService.getUsersByRole("Teacher").subscribe(
        data => {
          this.users = data
        },
        err => console.error(err),
        () => console.log('Done loading teacher users')
    );
  }

  isCollapsed: boolean = false;
  iconCollapse: string = 'icon-arrow-up';
  nationality: String;

  submitForm(data){
    let authObs: Observable<boolean>;
    data["role"] = "Teacher";
    authObs = this.userService.createUser(data);

    authObs.subscribe(
        resData => {
          console.log("User has been created" + data);
          this.getTeacherUsers();
          this.toggleCollapse();
        },
        errorMessage => {
          console.log(errorMessage);
        }
    );
  }

  createNewUser() {
    this.initialiseForm();
    this.toggleCollapse();
  }

  viewListUsers() {
    this.getTeacherUsers();
    this.toggleCollapse();
  }

  viewTeacherUser(teacherId:string) {
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
