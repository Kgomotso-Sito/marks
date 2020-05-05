import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Subject } from 'rxjs';

import { User } from './user.model';
import {AngularFireAuth} from "@angular/fire/auth";

@Injectable({ providedIn: 'root' })
export class LoginService {

  user = new Subject<User>();

  constructor(private http: HttpClient, private firebaseAuth: AngularFireAuth) {}


  login(email: string, password: string) {
    return this.firebaseAuth.signInWithEmailAndPassword(email,password);
  }

  public handleError(errorRes: any) {
    let errorMessage = 'An unknown error occurred!';

    switch (errorRes.code) {
      case 'EMAIL_EXISTS':
        errorMessage = 'This email exists already';
        break;
      case 'auth/user-not-found':
        errorMessage = 'This email does not exist.';
        break;
      case 'auth/wrong-password':
        errorMessage = 'Incorrect credentials';
        break;
    }
    return errorMessage;
  }
}
