// Angular
import { CommonModule } from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { NgModule } from '@angular/core';

// User Components
import { AdminComponent } from './admin/admin.component';
import { TeachersComponent } from './teachers/teachers.component';
import { LearnersComponent } from './learners/learners.component';

import {CollapseModule} from "ngx-bootstrap/collapse";

import { UsersRoutingModule } from './users-routing-module';
import { TabsModule } from 'ngx-bootstrap/tabs';
import {ModalModule, TooltipModule} from "ngx-bootstrap";

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        UsersRoutingModule,
        CollapseModule.forRoot(),
        TabsModule.forRoot(),
        ReactiveFormsModule,
        ModalModule.forRoot(),
        TooltipModule.forRoot()
    ],
  declarations: [
    AdminComponent,
    TeachersComponent,
    LearnersComponent
  ]
})
export class UsersModule { }
