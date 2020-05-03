import {Component, OnInit, ViewChild} from '@angular/core';
import {User} from "../user.model";
import {UserService} from "../user.service";
import {Observable} from "rxjs";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ModalDirective} from "ngx-bootstrap";

@Component({
  templateUrl: 'admin.component.html'
})
export class AdminComponent implements OnInit {

  users: User [];
  user: User;
  form: FormGroup;

  titles: string[];
  genders: string[];
  races: string[];
  provinces: string[];

  @ViewChild('warningModal') warningModal: ModalDirective;
  userNumber: string;

  constructor(private userService: UserService, private formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.titles = this.userService.getTitles();
    this.genders = this.userService.getGenders();
    this.races = this.userService.getRaces();
    this.provinces = this.userService.getProvinces();
    this.initialiseForm();
    this.getAdminUsers();
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

  getAdminUsers() {
    this.userService.getUsersByRole("Admin").subscribe(
      data => { this.users = data},
      err => console.error(err),
      () => console.log('Done loading admin users')
 );
}

  isCollapsed: boolean = false;
  iconCollapse: string = 'icon-arrow-up';
  nationality: String;

  submitForm(data){
    let authObs: Observable<boolean>;
    data["role"] = "Admin";
    authObs = this.userService.createUser(data);

    authObs.subscribe(
        resData => {
          console.log("User has been created" + data);
          this.getAdminUsers();
          this.toggleCollapse();
        },
        errorMessage => {
          console.log(errorMessage);
        }
    );
  }

  viewListUsers() {
      this.getAdminUsers();
      this.toggleCollapse();
  }

  createNewUser() {
    this.initialiseForm();
    this.toggleCollapse();
  }

  viewAdminUser(employeeId:string) {
    this.userService.getUserByUserId(employeeId).subscribe(
        data => {
          this.user = data;
          this.form.patchValue(this.user);
          this.toggleCollapse();
        },
        err => console.error(err),
        () => console.log('Done fetching admin user')
    );
  }

  deactivateAdminUser() {
    this.userService.deactivateUser(this.userNumber).subscribe(
        data => {
            console.log('Response: ' + data)
            this.getAdminUsers();
            this.warningModal.hide();
        },
        err => console.error(err),
        () => console.log('Done fetching admin user')
    );
  }

  collapsed(event: any): void {
    // console.log(event);
  }

  expanded(event: any): void {
    // console.log(event);
  }

  showWarning(userNumber: string) {
      this.userNumber = userNumber;
      this.warningModal.show();
  }

  toggleCollapse(): void {
    this.isCollapsed = !this.isCollapsed;
    this.iconCollapse = this.isCollapsed ? 'icon-arrow-down' : 'icon-arrow-up';
  }
}
