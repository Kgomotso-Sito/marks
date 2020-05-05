import { Component, OnInit } from '@angular/core';
import {Subject} from "./models/subjects.model";
import {Assessment} from "./models/assessment.model";

@Component({
  selector: 'app-subjects',
  templateUrl: './subjects.component.html',
})
export class SubjectsComponent implements OnInit {

  assessments: Array<Assessment> = [];
  newAssessments: any = {};

  constructor() { }

  ngOnInit(): void {
    this.newAssessments = {code: "", name: "", casWeigh:""};
    this.assessments.push(this.newAssessments);
  }

  addAssessment(index) {
    this.newAssessments = {code: "", name: "",casWeigh:""};
    this.assessments.push(this.newAssessments);
    //this.toastr.success('New assessment added successfully', 'New assessment');
    console.log(this.assessments);
    return true;
  }

  deleteAssessment(index) {
    if(this.assessments.length ==1) {
      //this.toastr.error("Can't delete the row when there is only one row", 'Warning');
      return false;
    } else {
      this.assessments.splice(index, 1);
      //this.toastr.warning('Row deleted successfully', 'Delete row');
      return true;
    }
  }

  submitForm(data){
    console.log(data);
  }
}
