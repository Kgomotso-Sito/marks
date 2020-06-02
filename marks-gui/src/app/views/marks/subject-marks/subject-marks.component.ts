import { Component, OnInit } from '@angular/core';
import {Assessment} from "../../subjects/models/assessment.model";
import {Subject} from "../../subjects/models/subjects.model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {SubjectService} from "../../subjects/subject.service";
import {Mark} from "../models/mark.model";
import {SubjectMarksService} from "./subject-marks.service";

@Component({
  selector: 'app-subject-marks',
  templateUrl: './subject-marks.component.html'
})
export class SubjectMarksComponent implements OnInit {

  assessments: Assessment[];
  assessment: Assessment;
  subjects: Subject [];
  subject: Subject;

  marks: Mark [];
  mark: Mark;

  form: FormGroup;

  hideSubjects: boolean = false;
  hideAssessments: boolean = true;
  hideUsers: boolean = true;

  constructor(private subjectService: SubjectService, private subjectMarksService: SubjectMarksService,private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.getSubjects();
  }

  getSubjects() {
    this.subjectService.getSubjects().subscribe(
        data => {
          this.subjects = data
        },
        err => console.error(err),
        () => console.log('Done loading subjects')
    );
  }

  viewSubject(subjectId :string) {
    this.subjectService.getSubject(subjectId).subscribe(
        data => {
          this.subject = data;
          this.prepareAssessment(subjectId);
          this.showAssessments()
        },
        err => console.error(err),
        () => console.log('Done fetching subject')
    );
  }

  prepareAssessment(subjectId : string) {
    this.subjectService.getAssessmentBySubject(subjectId).subscribe(
        data => {
          this.assessments = data;
        },
        err => console.error(err),
        () => console.log('Done fetching assessments')
    );
  }

  addMarks(assessmentId :number) {
      this.assessment = this.assessments[assessmentId];
      this.subjectMarksService.getMarksByAssessment(this.assessment.id).subscribe(
        data => {
            this.marks = data;
            this.showUsers();
        },
        err => console.error(err),
        () => console.log('Done enrolled users')
    );
  }

  saveMarks(userId :number) {
      this.mark = this.marks[userId];

      this.subjectMarksService.createMark(this.mark).subscribe(
        () => {
        },
        err => console.error(err),
        () => console.log('User mark saved')
    );
  }

  onKey(event: any, index: number) { // without type info
      this.marks[index].grade = event.target.value;
  }

  showSubjects(){
      this.hideSubjects = false;
      this.hideAssessments = true;
      this.hideUsers = true;
  }

  showAssessments(){
    this.hideSubjects = true;
    this.hideAssessments = false;
    this.hideUsers = true;
  }

  showUsers(){
    this.hideSubjects = true;
    this.hideAssessments = true;
    this.hideUsers = false;
  }

  collapsed(event: any): void {
    // console.log(event);
  }

  expanded(event: any): void {
    //console.log(event);
  }
}
