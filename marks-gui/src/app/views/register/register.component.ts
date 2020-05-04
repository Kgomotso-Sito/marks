import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {RegisterService} from "./register.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: 'register.component.html'
})
export class RegisterComponent implements OnInit{
  registerForm: FormGroup;
  isLoginMode = false;
  isLoading = false;
  message: string;
  success: boolean = false;

  private submitted: boolean = false;

  constructor(private registerService: RegisterService, private formBuilder: FormBuilder){
  }

  ngOnInit() {
    this.initialiseForm();
  }

  initialiseForm() {
    this.registerForm = this.formBuilder.group(
        {
          schoolId : this.formBuilder.control("", [Validators.required,
            Validators.minLength(9), Validators.maxLength(9)]),
          email : this.formBuilder.control("", [Validators.required,
            Validators.email]),
          password : this.formBuilder.control("", [Validators.required,
            Validators.minLength(5)]),
          confirmPassword : this.formBuilder.control("", Validators.required)
        },
        {validator: this.mustMatch('password', 'confirmPassword')}
  )};

  get f() { return this.registerForm.controls; }

  mustMatch(controlName: string, matchingControlName: string) {
    return (formGroup: FormGroup) => {
      const control = formGroup.controls[controlName];
      const matchingControl = formGroup.controls[matchingControlName];

      if (matchingControl.errors && !matchingControl.errors.mustMatch) {
        // return if another validator has already found an error on the matchingControl
        return;
      }

      // set error on matchingControl if validation fails
      if (control.value !== matchingControl.value) {
        matchingControl.setErrors({ mustMatch: true });
      } else {
        matchingControl.setErrors(null);
      }
    }
  }

  public findInvalidControls() {
    let invalid : string[] = [];
    const controls = this.registerForm.controls;
    for (const name in controls) {
      if (controls[name].invalid) {
        invalid.push(name);
      }
    }
    return invalid;
  }

  onSubmit(data) {
    this.submitted = true;
    this.success = false;
    if (!this.registerForm.valid) {
      console.log("This field " + this.findInvalidControls() + " is invalid");
      return;
    }
    const email = data.email;
    const password = data.password;

    this.isLoading = true;

    this.registerService.signup(email, password).subscribe(
      resData => {
        console.log(resData);
        this.message = "User account was created, you can go log in now";
        this.success = true;
        this.isLoading = false;
      },
      errorMessage => {
        console.log(errorMessage);
        this.message = errorMessage;
        this.isLoading = false;
      }
    );

    this.registerForm.reset();
  }
}
