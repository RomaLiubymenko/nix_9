import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {StudentService} from "../../../shared/services/student.service";
import {AbstractTableComponent} from "../../../shared/components/abstract-table/abstract-table.component";
import {Student} from 'src/app/shared/models/student.model';
import {ConfirmationService, MessageService, SortEvent} from "primeng/api";

@Component({
  selector: 'student-table',
  templateUrl: './student-table.component.html',
  styleUrls: ['./student-table.component.scss']
})
export class StudentTableComponent extends AbstractTableComponent<Student> implements OnInit {

  columns = [
    {field: 'firstName', header: 'First Name'},
    {field: 'lastName', header: 'Last Name'},
    {field: 'birthDay', header: 'BirthDay'},
    {field: 'studentGroups', header: 'Student Groups'},
  ];

  constructor(
    public override router: Router,
    public override activatedRoute: ActivatedRoute,
    public studentService: StudentService,
    public override messageService: MessageService,
    public override confirmationService: ConfirmationService
  ) {
    super(studentService, router, activatedRoute, messageService, confirmationService)
  }

  addStudent(): void {
    this.router.navigate(['students', 'new-student'])
  }

  editStudent(student: Student): void {
    this.router.navigate(['students', student.uuid, 'edit-form'])
  }

  override sortBy(event: SortEvent) {
    if (event.field == 'studentGroups') {
      // pass
      this.predicate = 'created';
    } else {
      this.predicate = event.field !== undefined ? event.field : 'created';
    }
    this.isAscending = event.order == 1;
    this.transition();
  }
}
