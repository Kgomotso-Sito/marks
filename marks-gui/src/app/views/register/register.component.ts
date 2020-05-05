import { Component } from '@angular/core';
import {NgForm} from "@angular/forms";
import {RegisterService} from "./register.service";
import {throwError, Observable} from "rxjs";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-dashboard',
  templateUrl: 'register.component.html'
})
export class RegisterComponent {
  isLoginMode = false;
  isLoading = false;
  error: string = null;

  constructor(private registerService: RegisterService) {
  }

  onSubmit(form: NgForm) {
    if (!form.valid) {
      return;
    }
    const email = form.value.email;
    const password = form.value.password;

    this.isLoading = true;

    this.registerService.signup(email, password).subscribe(
      resData => {
        console.log(resData);
        this.isLoading = false;
      },
      errorMessage => {
        console.log(errorMessage);
        this.error = errorMessage;
        this.isLoading = false;
      }
    );

    console.log("Hello World");
    form.reset();
  }
}
