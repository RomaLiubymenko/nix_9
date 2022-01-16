import {Component, OnInit} from '@angular/core';
import {Student} from "../../../shared/models/student.model";
import {ActivatedRoute, Router} from "@angular/router";
import {StudentService} from "../../../shared/services/student.service";
import * as moment from 'moment';
import {StudentGroupService} from "../../../shared/services/student-group.service";
import {StudentGroup} from 'src/app/shared/models/student-group.model';
import {forkJoin, ObservableInput} from "rxjs";
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
  groups: StudentGroup[];
  groupsForSelect: any[] = [];
  isLoading: boolean = true;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private studentService: StudentService,
    private studentGroupService: StudentGroupService,
    private confirmationService: ConfirmationService) {
  }

  ngOnInit(): void {
    this.route.data.subscribe(({student}) => {
      this.student = student;
      if (!!student.birthDay) {
        this.date = moment(student.birthDay, 'DD-MM-YYYY').toDate();
      }
      if (this.student.studentGroupUuids?.length) {
        // todo: fix it
        const observableStudentGroups$: ObservableInput<any>[] = [];
        this.student.studentGroupUuids.forEach(uuid => {
          observableStudentGroups$.push(this.studentGroupService.getByUuid(uuid));
        });
        forkJoin(observableStudentGroups$).subscribe((groups: StudentGroup[]) => {
          this.groups = groups;
          this.isLoading = false;
        });
      } else {
        this.isLoading = false;
      }
      this.createGroupForSelect();
    });
  }

  save() {
    this.student.birthDay = this.date;
    this.confirmationService.confirm({
      message: 'Are you sure you want to save entry?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.studentService.save(this.student).subscribe(() => this.router.navigate(['students']))
      }
    });
  }

  createGroupForSelect() {
    this.studentGroupService.getAll().subscribe((groupRes: HttpResponse<StudentGroup[]>) => {
      groupRes.body!.forEach(group => {
        this.groupsForSelect.push({name: group.name, code: group.uuid})
      })
    });
  }
}

