import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {GroupType, StudentGroup} from 'src/app/shared/models/student-group.model';
import {StudentGroupService} from "../../../shared/services/student-group.service";
import {Student} from "../../../shared/models/student.model";
import {ConfirmationService, MessageService} from "primeng/api";
import {StudentService} from "../../../shared/services/student.service";
import {HttpResponse} from "@angular/common/http";

@Component({
  selector: 'student-group-edit',
  templateUrl: './student-group-edit.component.html',
  styleUrls: ['./student-group-edit.component.scss']
})
export class StudentGroupEditComponent implements OnInit {
  // todo: add validation

  group: StudentGroup;
  students: Student[] = [];
  selectedStudentUuids: string[] = []
  studentsForSelect: any[] = [];
  isLoading: boolean = true;

  groupTypes = [
    {name: 'Group', code: GroupType.GROUP},
    {name: 'Mini group', code: GroupType.MINI_GROUP},
    {name: 'Individual', code: GroupType.INDIVIDUAL}
  ];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private studentGroupService: StudentGroupService,
    private studentService: StudentService,
    private confirmationService: ConfirmationService,
    private messageService: MessageService) {
  }

  ngOnInit(): void {
    this.route.data.subscribe(({group}) => {
      this.group = group;
      this.setSelectedStudent();
      this.createStudentsForSelect();
    });

  }

  save() {
    this.confirmationService.confirm({
      message: 'Are you sure you want to save group ?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.preparedStudentForSave();
        this.studentGroupService.save(this.group).subscribe({
          next: () => this.router.navigate(['student-groups']),
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

  createStudentsForSelect() {
    this.studentService.getAll().subscribe((studentRes: HttpResponse<Student[]>) => {
      this.students = studentRes.body!.map(student => {
        this.studentsForSelect.push({name: student.lastName + " " + student.firstName, code: student.uuid})
        this.isLoading = false;
        return student;
      })
    });
  }

  setSelectedStudent() {
    this.selectedStudentUuids = this.group.students!?.map(student => student.uuid!);
  }

  preparedStudentForSave() {
    if (this.selectedStudentUuids.length) {
      this.group.students = this.students.filter(student => this.selectedStudentUuids.includes(student.uuid!));
    } else {
      this.group.students = [];
    }
  }
}
