import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {ConfirmationService, MessageService} from "primeng/api";
import {AbstractTableComponent} from "../../../shared/components/abstract-table/abstract-table.component";
import {StudentGroup} from "../../../shared/models/student-group.model";
import {StudentGroupService} from "../../../shared/services/student-group.service";

@Component({
  selector: 'student-group-table',
  templateUrl: './student-group-table.component.html',
  styleUrls: ['./student-group-table.component.scss']
})
export class StudentGroupTableComponent extends AbstractTableComponent<StudentGroup> implements OnInit {

  columns = [
    {field: 'name', header: 'Group name',},
    {field: 'description', header: 'Description'},
    {field: 'groupType', header: 'Group type'},
  ];

  constructor(
    public override router: Router,
    public override activatedRoute: ActivatedRoute,
    public studentGroupService: StudentGroupService,
    public override messageService: MessageService,
    public override confirmationService: ConfirmationService
  ) {
    super(studentGroupService, router, activatedRoute, messageService, confirmationService)
  }

  addStudentGroup(): void {
    this.router.navigate(['student-groups', 'new-student-group'])
  }

  editStudentGroup(studentGroup: StudentGroup): void {
    this.router.navigate(['student-groups', studentGroup.uuid, 'edit-form'])
  }
}
