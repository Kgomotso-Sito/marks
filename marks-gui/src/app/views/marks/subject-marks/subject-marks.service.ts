import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Mark} from "../models/mark.model";


@Injectable({ providedIn: 'root' })
export class SubjectMarksService {

    url : string = "http://localhost:8080/marks";

    constructor(private http: HttpClient) {}

    public createMark(mark: Mark) {
        console.log("Sending mark..." + JSON.stringify(mark));
        return this.http
            .post<boolean>(this.url + "/create", mark);
    }

    public getMarksByAssessment(assessmentId: any) {
        console.log("Sending..." + JSON.stringify(assessmentId));
        return this.http
            .get<Mark []>(this.url + "/assessment/" + assessmentId);
    }

    public enrollForSubject(userSubject: any) {
        console.log("Sending subject..." + JSON.stringify(userSubject));
        return this.http
            .post<boolean>(this.url + "/enroll", userSubject);
    }

}
