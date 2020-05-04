import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {LoginService} from "../login/login.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Observable} from "rxjs";


interface AuthResponseData {
  kind: string;
  idToken: string;
  email: string;
  refreshToken: string;
  expiresIn: string;
  localId: string;
}

@Component({
  selector: 'app-dashboard',
  templateUrl: 'login.component.html'
})
export class LoginComponent implements OnInit{
  error: string = null;
  offlineDebug: boolean = true;
  private submitted: boolean = false;

  loginForm: FormGroup;

  constructor(private router: Router, private loginService: LoginService, private formBuilder: FormBuilder){}

  ngOnInit() {
    this.initialiseForm();
  }

  initialiseForm() {
    this.loginForm = this.formBuilder.group(
        {
          email : this.formBuilder.control("", [Validators.required,
           Validators.email]),
          password : this.formBuilder.control("", [Validators.required,
            Validators.minLength(5)])
        }
    )};

  onSubmit(data) {
    this.submitted = true;

    if (!this.loginForm.valid) {
      return;
    }
    const email = data.email;
    const password = data.password;

    if(this.offlineDebug) {
      let authObs: Observable<AuthResponseData>;

      authObs = this.loginService.login(email, password);

      authObs.subscribe(
        resData => {
          console.log(resData);
          this.router.navigate(['/users/admin']);
        },
        errorMessage => {
          console.log(errorMessage);
          this.error = errorMessage;
        }
      );

      this.loginForm.reset();
    } else {
        //this.router.navigate(['/dashboard']);//
    }
  }
  get f() { return this.loginForm.controls; }

  goToRegister() {
    this.router.navigate(['/register']);
  }
}
