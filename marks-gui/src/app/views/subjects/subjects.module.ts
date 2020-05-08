import { NgModule } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { ButtonsModule } from 'ngx-bootstrap/buttons';

import { SubjectsComponent } from './subjects.component';
import { SubjectsRoutingModule } from './subjects-routing.module';
import {CommonModule} from "@angular/common";
import {CollapseModule} from "ngx-bootstrap/collapse";

@NgModule({
  imports: [
    FormsModule,
    SubjectsRoutingModule,
    BsDropdownModule,
    ButtonsModule.forRoot(),
    CollapseModule.forRoot(),
    CommonModule,
    ReactiveFormsModule
  ],
  declarations: [ SubjectsComponent ]
})
export class SubjectsModule { }
