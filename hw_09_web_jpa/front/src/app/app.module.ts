import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {StudentTableComponent} from './entities/student/student-table/student-table.component';
import {StudentEditComponent} from './entities/student/student-edit/student-edit.component';
import {StudentGroupTableComponent} from './entities/student-group/student-group-table/student-group-table.component';
import {StudentGroupEditComponent} from './entities/student-group/student-group-edit/student-group-edit.component';
import {MDBBootstrapModule} from "angular-bootstrap-md";
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NavigationModule} from "./shared/layout/navigation/navigation.module";
import {RouterModule} from "@angular/router";
import {AppRoutingModule} from "./app-routing.module";
import {PaginationModule} from "./shared/components/pagination/pagination.module";
import {ToastModule} from "primeng/toast";
import {ToolbarModule} from "primeng/toolbar";
import {TableModule} from "primeng/table";
import {ConfirmDialogModule} from "primeng/confirmdialog";
import {MultiSelectModule} from "primeng/multiselect";
import {DropdownModule} from "primeng/dropdown";
import {SliderModule} from "primeng/slider";
import {DialogModule} from "primeng/dialog";
import {CalendarModule} from "primeng/calendar";
import {ContextMenuModule} from "primeng/contextmenu";
import {ButtonModule} from "primeng/button";
import {InputTextModule} from "primeng/inputtext";
import {FileUploadModule} from "primeng/fileupload";
import {HttpClientModule} from "@angular/common/http";
import {ProgressBarModule} from "primeng/progressbar";
import {RatingModule} from "primeng/rating";
import {FormsModule} from "@angular/forms";
import {RadioButtonModule} from "primeng/radiobutton";
import {InputNumberModule} from "primeng/inputnumber";
import {InputTextareaModule} from "primeng/inputtextarea";
import {RippleModule} from "primeng/ripple";
import {ConfirmationService, MessageService} from "primeng/api";
import {CardModule} from "primeng/card";

// todo: transfer all entities to modules

const PRIMENG_MODULES = [
  TableModule,
  CalendarModule,
  SliderModule,
  DialogModule,
  MultiSelectModule,
  ContextMenuModule,
  DropdownModule,
  ButtonModule,
  ToastModule,
  InputTextModule,
  ProgressBarModule,
  HttpClientModule,
  FileUploadModule,
  ToolbarModule,
  RatingModule,
  FormsModule,
  RadioButtonModule,
  InputNumberModule,
  ConfirmDialogModule,
  InputTextareaModule,
  RippleModule,
  CardModule,
  InputTextModule
];

const MDB_BOOTSTRAP_MODULES = [
  MDBBootstrapModule.forRoot()
];

@NgModule({
  declarations: [
    AppComponent,
    StudentTableComponent,
    StudentEditComponent,
    StudentGroupTableComponent,
    StudentGroupEditComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    RouterModule,
    AppRoutingModule,
    NavigationModule,
    PaginationModule,
    ...MDB_BOOTSTRAP_MODULES,
    ...PRIMENG_MODULES
  ],
  providers: [MessageService, ConfirmationService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
