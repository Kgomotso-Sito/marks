import {Component, OnInit, ViewChild} from '@angular/core';
import {User} from "../user.model";
import {UserService} from "../user.service";
import {Observable} from "rxjs";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
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
  private submitted: boolean = false;

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
            Validators.minLength(12), Validators.maxLength(13)]),
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
      }
    )
  }

  // convenience getter for easy access to form fields
  get f() { return this.form.controls; }

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

    public findInvalidControls() {
         let invalid : string[] = [];
        const controls = this.form.controls;
        for (const name in controls) {
            if (controls[name].invalid) {
                invalid.push(name);
            }
        }
        return invalid;
    }

  submitForm(data){
    this.submitted = true;
    if (this.form.invalid) {
        console.log("This field " + this.findInvalidControls() + " is invalid");
      return;
    }

    let authObs: Observable<boolean>;
    data["role"] = "Admin";
    authObs = this.userService.createUser(data);

    authObs.subscribe(
        resData => {
          console.log("User has been created" + resData);
          this.getAdminUsers();
          this.toggleCollapse();
        },
        errorMessage => {
          console.log(errorMessage);
        }
    );
  }

  onReset() {
    this.submitted = false;
    this.form.reset();
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
            console.log('Response: ' + data);
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
