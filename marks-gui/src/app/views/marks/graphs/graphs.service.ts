import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Grade} from "../models/grades.model";

@Injectable({ providedIn: 'root' })
export class GraphsService {

    url : string = "https://mark-api-gateway-1591161650651.herokuapp.com/subjects";

    constructor(private http: HttpClient) {}

    public getMarks() {
        return this.http
            .get<Grade []>(this.url + "/marks");
    }

}