import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {LoginService} from "../login/login.service";
import {NgForm} from "@angular/forms";
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
export class LoginComponent {
  error: string = null;
  offlineDebug: boolean = false;

  constructor(private router: Router, private loginService: LoginService) { }

  onSubmit(form: NgForm) {
    if (!form.valid) {
      return;
    }
    const email = form.value.email;
    const password = form.value.password;

    if(this.offlineDebug) {
      let authObs: Observable<AuthResponseData>;

      authObs = this.loginService.login(email, password);

      authObs.subscribe(
        resData => {
          console.log(resData);
          this.router.navigate(['/dashboard']);
        },
        errorMessage => {
          console.log(errorMessage);
          this.error = errorMessage;
        }
      );

      form.reset();
    } else {
        this.router.navigate(['/dashboard']);//
    }
  }

  goToRegister() {
    this.router.navigate(['/register']);
  }
}
