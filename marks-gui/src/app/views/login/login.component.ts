import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {LoginService} from "../login/login.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AngularFireAuth} from "@angular/fire/auth";


@Component({
  selector: 'app-dashboard',
  templateUrl: 'login.component.html'
})
export class LoginComponent implements OnInit{
  error: string = null;
  offlineDebug: boolean = true;
  private submitted: boolean = false;
  private success: boolean = true;
  private loading: boolean = false;

  loginForm: FormGroup;

  constructor(private router: Router, private loginService: LoginService, private formBuilder: FormBuilder) {}

  ngOnInit() {
      /*if (this.firebaseAuth.currentUser) {
        console.log(this.firebaseAuth.currentUser)
        this.router.navigateByUrl('/users/admin');
      } else {
        this.initialiseForm();
      }*/
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

  onSubmit(formData) {
    this.submitted = false;

    if (!this.loginForm.valid) {
      this.submitted = true;
      return;
    }
    this.loading = true;
    if(this.offlineDebug) {
      this.loginService.login(formData.email, formData.password).then(
          () => {
            this.router.navigate(['/users/admin']);
          }
      ).catch(errorMessage => {
        this.loading = false;
        console.log(errorMessage);
        this.success = false;
        this.error = this.loginService.handleError(errorMessage);
      });
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
