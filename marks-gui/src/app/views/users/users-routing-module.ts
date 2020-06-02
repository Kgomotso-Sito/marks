import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AdminComponent } from './admin/admin.component';
import { TeachersComponent } from "./teachers/teachers.component";
import { LearnersComponent } from "./learners/learners.component";

const routes: Routes = [
  {
    path: '',
    data: {
      title: 'Users'
    },
    children: [
      {
        path: '',
        redirectTo: 'admin'
      },
      {
        path: 'admin',
        component: AdminComponent,
        data: {
          title: 'Admin'
        }
      },
      {
        path: 'teacher',
        component: TeachersComponent,
        data: {
          title: 'Teacher'
        }
      },
      {
        path: 'learner',
        component: LearnersComponent,
        data: {
          title: 'Learner'
        }
      }
    ]
  }
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsersRoutingModule {
}
