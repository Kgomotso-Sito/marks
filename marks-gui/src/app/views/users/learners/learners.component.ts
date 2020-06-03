import {Component, OnInit, ViewChild} from '@angular/core';
import {User} from "../user.model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../user.service";
import {Observable} from "rxjs";
import {ModalDirective} from "ngx-bootstrap";

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

  @ViewChild('warningModal') warningModal: ModalDirective;
  userNumber: string;
  private submitted: boolean = false;

  constructor(private userService: UserService, private formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.titles = this.userService.getTitles();
    this.schoolClasses = this.userService.getClasses();
    this.schoolGrades = this.userService.getSchoolGrades();
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
    })
  }

    // convenience getter for easy access to form fields
    get f() { return this.form.controls; }

    onReset() {
        this.submitted = false;
        this.form.reset();
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
    this.submitted = true;
    if (this.form.invalid) {
      return;
    }

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

  deactivateLearnerUser() {
      this.userService.deactivateUser(this.userNumber).subscribe(
          data => {
              console.log('Response: ' + data);
              this.getLearners();
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
