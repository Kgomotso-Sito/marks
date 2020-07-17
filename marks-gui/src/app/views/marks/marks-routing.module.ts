import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SubjectMarksComponent } from "./subject-marks/subject-marks.component";
import { SubjectAssessmentMarksComponent } from "./subject-assessment-marks/subject-assessment-marks.component";
import { GraphsComponent } from "./graphs/graphs.component";


const routes: Routes = [
  {
    path: '',
    data: {
      title: 'Marks'
    },
    children: [
      {
        path: '',
        redirectTo: 'subjects'
      },
      {
        path: 'subjects',
        component: SubjectMarksComponent,
        data: {
          title: 'Subjects'
        }
      },
      {
        path: 'assessments',
        component: SubjectAssessmentMarksComponent,
        data: {
          title: 'Assessments'
        }
      },
      {
        path: 'graphs',
        component: GraphsComponent,
        data: {
          title: 'Graphs'
        }
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MarksRoutingModule {}
