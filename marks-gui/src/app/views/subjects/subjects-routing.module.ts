import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { SubjectsComponent } from './subjects.component';
import {EnrollmentComponent} from "./enrollment/enrollment.component";

const routes: Routes = [
  {
    path: '',

    data: {
      title: 'Subjects'
    },
    children: [
      {
        path: '',
        redirectTo: 'subjects'
      },
      {
        path: 'subjects',
        component: SubjectsComponent,
        data: {
          title: 'Subjects'
        }
      },
      {
        path: 'enrollment',
        component: EnrollmentComponent,
        data: {
          title: 'Enrollment'
        }
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SubjectsRoutingModule {}
