import { Injectable } from '@angular/core';
import {User} from "./user.model";
import {HttpClient} from "@angular/common/http";



@Injectable({ providedIn: 'root' })
export class UserService {

    url : string = "http://localhost:8080/users";

    constructor(private http: HttpClient) {}

    public getUsersByRole(role: string) {
        return this.http
            .get<User []>(this.url + "/all/"+ role);
    }

    public createUser(user: any) {
        console.log("Sending ..." + JSON.stringify(user));
        return this.http
            .post<boolean>(this.url + "/create", user);
    }

    public getUserByUserId(userId: string) {
        console.log("Sending ..." + JSON.stringify(userId));
        return this.http
            .get<User>(this.url + "/" + userId);
    }

    public getTitles() {
        return ["Mr", "Mrs", "Ms", "Dr", "Prof"];
    }

    public getSchoolGrades() {
        return ["10", "11", "12"];
    }

    public getClasses() {
        return ["A", "B", "C", "D", "E"];
    }

    public getGenders() {
        return ["Male", "Female"];
    }

    public getRaces() {
        return ["Black", "White", "Coloured", "Asian"];
    }

    public getProvinces() {
        return ["Gauteng", "North West", "Kwa-Zulu Natal", "Mpumalanga", "Limpopo", "Western Cape", "Eastern Cape", "Northen Cape", "Free State"];
    }
}