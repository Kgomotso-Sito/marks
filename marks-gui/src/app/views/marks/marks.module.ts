import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { ButtonsModule } from 'ngx-bootstrap/buttons';

import { MarksRoutingModule } from './marks-routing.module';
import {CommonModule} from "@angular/common";
import { SubjectMarksComponent } from './subject-marks/subject-marks.component';
import { SubjectAssessmentMarksComponent } from './subject-assessment-marks/subject-assessment-marks.component';
import { GraphsComponent } from './graphs/graphs.component';
import { ChartsModule } from "ng2-charts";
import {CollapseModule} from "ngx-bootstrap/collapse";

@NgModule({
  imports: [
    FormsModule,
    MarksRoutingModule,
    BsDropdownModule,
    ButtonsModule.forRoot(),
    CollapseModule.forRoot(),
    CommonModule,
    ChartsModule
  ],
  declarations: [ SubjectMarksComponent, SubjectAssessmentMarksComponent, GraphsComponent ]
})
export class MarksModule { }
