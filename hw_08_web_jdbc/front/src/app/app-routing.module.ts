import {RouterModule, Routes} from "@angular/router";
import {navigationRoute} from "./shared/layout/navigation/navigation.route";
import {NgModule} from "@angular/core";
import {studentsRoute} from "./entities/student/student.route";
import {studentGroupsRoute} from "./entities/student-group/student-group.route";

const LAYOUT_ROUTES = [navigationRoute];

const appRoutes: Routes = [
  {path: 'student-groups', data: {breadcrumb: 'Student groups'}, children: studentGroupsRoute},
  {path: 'students', data: {breadcrumb: 'Students'}, children: studentsRoute},
]

@NgModule({
  imports: [
    RouterModule.forRoot(
      [
        ...LAYOUT_ROUTES,
        ...appRoutes
      ]
    )
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
