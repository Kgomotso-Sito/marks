import {Component, OnInit, ViewChild} from '@angular/core';
import {UserService} from "../user.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {User} from "../user.model";
import {Observable} from "rxjs";
import {ModalDirective} from "ngx-bootstrap";

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

  @ViewChild('warningModal') warningModal: ModalDirective;
  userNumber: string;
  submitted: boolean = false;

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
        title : this.formBuilder.control("", Validators.required),
        initials : this.formBuilder.control("", Validators.required),
        fullName : this.formBuilder.control("", Validators.required),
        lastName : this.formBuilder.control("", Validators.required),
        gender : this.formBuilder.control("", Validators.required),
        race : this.formBuilder.control("", Validators.required),
        otherNationality : "",
        birthDate : this.formBuilder.control("", Validators.required),
        nationality : this.formBuilder.control("", Validators.required),
        idNumber :  this.formBuilder.control("", [Validators.required, Validators.pattern("^[0-9]*$"),
            Validators.minLength(12), Validators.maxLength(12)]),
        passportNumber : "",
        emailAddress : this.formBuilder.control("", [Validators.required, Validators.email]),
        phoneNumber : this.formBuilder.control("", [Validators.required, Validators.pattern("^[0-9]*$"),
            Validators.minLength(10), Validators.maxLength(10)]),
        houseNo : this.formBuilder.control("", Validators.required),
        city : this.formBuilder.control("", Validators.required),
        cityCode : this.formBuilder.control("", [Validators.required, Validators.pattern("^[0-9]*$")]),
        province : this.formBuilder.control("", Validators.required),
        active : true,
        streetNo : this.formBuilder.control("", Validators.required),
    })
  }
  onReset() {
    this.submitted = false;
    this.form.reset();
  }

    // convenience getter for easy access to form fields
  get f() { return this.form.controls; }

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
    this.submitted = true;
    if (this.form.invalid) {
        return;
    }

    let authObs: Observable<boolean>;
    data["role"] = "Teacher";
    authObs = this.userService.createUser(data);

    authObs.subscribe(
        resData => {
          console.log("User has been created" + resData);
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

  deactivateTeacherUser() {
      this.userService.deactivateUser(this.userNumber).subscribe(
        data => {
            console.log('Response: ' + data);
            this.getTeacherUsers();
            this.warningModal.hide();
        },
        err => console.error(err),
        () => console.log('Done fetching admin user')
    );
  }

  showWarning(userNumber: string) {
    this.userNumber = userNumber;
    this.warningModal.show();
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
