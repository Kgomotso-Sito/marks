import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Subject} from "./models/subjects.model";
import {Assessment} from "./models/assessment.model";


@Injectable({ providedIn: 'root' })
export class SubjectService {

    url : string = "https://registry-1591161650651-mark-api-gateway-1591161650651.azuremicroservices.io";
    subjectURL : string = this.url +"/subjects";
    assessmentURL : string = this.url +"/assessment";

    constructor(private http: HttpClient) {}

    public createSubject(subject: any) {
        console.log("Sending subject..." + JSON.stringify(subject));
        return this.http
            .post<boolean>(this.subjectURL + "/create", subject);
    }

    public getSubjects() {
        return this.http
            .get<Subject []>(this.subjectURL + "/all");
    }

    public getUserSubjects(userId: string) {
        return this.http
            .get<Subject []>(this.subjectURL + "/user/" + userId);
    }

    public getSubject(subjectId: string) {
        console.log("Sending ..." + JSON.stringify(subjectId));
        return this.http
            .get<Subject>(this.subjectURL + "/" + subjectId);
    }

    public createAssessment(assessment: any) {
        console.log("Sending assessment..." + JSON.stringify(assessment));
        return this.http
            .post<boolean>(this.assessmentURL + "/create", assessment);
    }

    public getAssessmentBySubject(subjectId: string) {
        console.log("Sending..." + JSON.stringify(subjectId));
        return this.http
            .get<Assessment []>(this.assessmentURL + "/subject/" + subjectId);
    }

    public enrollForSubject(userSubject: any) {
        console.log("Sending subject..." + JSON.stringify(userSubject));
        return this.http
            .post<boolean>(this.subjectURL + "/enroll", userSubject);
    }

    public deregisterSubject(userSubject: any) {
        console.log("Sending subject..." + JSON.stringify(userSubject));
        return this.http
            .post<boolean>(this.subjectURL + "/deregister", userSubject);
    }
}
