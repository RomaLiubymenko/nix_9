import {Component, OnInit} from '@angular/core';
import {Student} from "../../../shared/models/student.model";
import {ActivatedRoute, Router} from "@angular/router";
import {StudentService} from "../../../shared/services/student.service";
import * as moment from 'moment';
import {StudentGroupService} from "../../../shared/services/student-group.service";
import {StudentGroup} from 'src/app/shared/models/student-group.model';
import {HttpResponse} from "@angular/common/http";
import {ConfirmationService, MessageService} from "primeng/api";

@Component({
  selector: 'student-edit',
  templateUrl: './student-edit.component.html',
  styleUrls: ['./student-edit.component.scss']
})
export class StudentEditComponent implements OnInit {

  // todo: add validation
  student: Student;
  date: Date;
  selectedGroupUuids: string[] = [];
  groupsForSelect: any[] = [];
  groups: StudentGroup[] = [];
  isLoading: boolean = true;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private studentService: StudentService,
    private studentGroupService: StudentGroupService,
    private confirmationService: ConfirmationService,
    private messageService: MessageService) {
  }

  ngOnInit(): void {
    this.route.data.subscribe(({student}) => {
      this.student = student;
      this.setSelectedGroup();
      if (!!student.birthDay) {
        this.date = moment(student.birthDay, 'DD-MM-YYYY').toDate();
      }
      this.createGroupForSelect();
    });
  }

  save() {
    this.student.birthDay = this.date;
    this.confirmationService.confirm({
      message: 'Are you sure you want to save student ?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.preparedGroupForSave();
        this.studentService.save(this.student).subscribe({
          next: () => this.router.navigate(['students']),
          error: err => {
            this.messageService.add({
              severity: 'error',
              summary: 'Error',
              detail: err.error.error.join('\n'),
              life: 20000
            });
          }
        });
      }
    });
  }

  createGroupForSelect() {
    this.studentGroupService.getAll().subscribe((groupRes: HttpResponse<StudentGroup[]>) => {
      this.groups = groupRes.body!.map(group => {
        this.groupsForSelect.push({name: group.name, code: group.uuid});
        this.isLoading = false;
        return group;
      })
    });
  }

  setSelectedGroup() {
    this.selectedGroupUuids = this.student.studentGroups!?.map(group => group.uuid!);
  }

  preparedGroupForSave() {
    if (this.selectedGroupUuids.length) {
      this.student.studentGroups = this.groups.filter(group => this.selectedGroupUuids.includes(group.uuid!));
    } else {
      this.student.studentGroups = [];
    }
  }
}

