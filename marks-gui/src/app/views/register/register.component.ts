import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {RegisterService} from "./register.service";
import {AngularFireAuth} from "@angular/fire/auth";

@Component({
  selector: 'app-dashboard',
  templateUrl: 'register.component.html'
})
export class RegisterComponent implements OnInit{
  registerForm: FormGroup;
  isLoginMode = false;
  isLoading: boolean = false;
  message: string;
  success: boolean = false;

  submitted: boolean = false;

  constructor(private registerService: RegisterService, private formBuilder: FormBuilder, private firebaseAuth: AngularFireAuth){
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

  onSubmit(formData) {
    this.submitted = false;

    if (!this.registerForm.valid) {
      this.submitted = true;
      return;
    }

    this.isLoading = true;
    this.registerService.checkIfUserExists(formData.schoolId).subscribe(
        data => {
          this.submitted = true;
          if(data == null) {
            console.log("User does not exist");
            this.message = "School ID does not exist, please contact school admin";
            this.success = false;
            this.isLoading = false;
          } else {
            console.log("User exists");
            this.success = true;
            this.registerUser(formData.email, formData.password);
            this.submitted = false;
          }
        },
        err => console.error(err),
        () => {
          console.log('Done fetching admin user');
        }
    );


  }

  registerUser(email: string, password: string) {
    this.firebaseAuth
        .createUserWithEmailAndPassword(email, password).then(
        resData => {
          console.log(resData);
          this.message = "User account was created, you can go log in now";
          this.success = true;
          this.isLoading = false;
          this.registerForm.reset();
        })
        .catch (errorMessage=> {
          this.submitted = true;
          console.log(errorMessage);
          this.success = false;
          this.message = errorMessage;
          this.isLoading = false;
        }
    );
  }
}
