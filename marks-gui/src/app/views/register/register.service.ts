import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {UserService} from "../users/user.service";


@Injectable({ providedIn: 'root' })
export class RegisterService {
  constructor(private http: HttpClient, private userService: UserService) {}

  checkIfUserExists(userId: string) {
      return this.userService.getUserByUserId(userId);
  }
}
