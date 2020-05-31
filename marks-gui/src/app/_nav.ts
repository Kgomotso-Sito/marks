import { INavData } from '@coreui/angular';

export const navItems: INavData[] = [
  {
    name: 'J.M Ntsime HS',
    url: '/users',
    icon: 'icon-home'
  },
  {
    name: 'Users',
    url: '/users',
    icon: 'cui-people',
    children: [
      {
        name: 'Admin',
        url: '/users/admin',
        icon: 'icon-user-following'
      },
      {
        name: 'Teachers',
        url: '/users/teacher',
        icon: 'cui-user'
      },
      {
        name: 'Learners',
        url: '/users/learner',
        icon: 'cui-user'
      }
    ]
  },

  {
    name: 'Subjects',
    url: '/subjects',
    icon: 'icon-notebook',
    children: [
      {
        name: 'View Subjects',
        url: '/subjects/subjects',
        icon: 'icon-notebook'
      },
      {
        name: 'Student Enrollment',
        url: '/subjects/enrollment',
        icon: 'icon-user-follow'
      }
    ]
  },
  {
    name: 'Marks',
    url: '/marks',
    icon: 'cui-task',
    children: [
      {
        name: 'Subject Marks',
        url: '/marks/subjects',
        icon: 'icon-notebook'
      },
      /*{
        name: 'Assessments',
        url: '/marks/assessments',
        icon: 'icon-note'
      },*/
      {
        name: 'Graphs',
        url: '/marks/graphs',
        icon: 'icon-note'
      },
      {
        name: 'Spreadsheet Template',
        url: '/theme/colors',
        icon: 'icon-note'
      }
    ]
  }
];
