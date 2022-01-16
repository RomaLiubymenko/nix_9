import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {GroupType, StudentGroup} from 'src/app/shared/models/student-group.model';
import {StudentGroupService} from "../../../shared/services/student-group.service";
import {Student} from "../../../shared/models/student.model";
import {ConfirmationService} from "primeng/api";
import {forkJoin, ObservableInput} from "rxjs";
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
  students: Student[];
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
    private confirmationService: ConfirmationService) {
  }

  ngOnInit(): void {
    this.route.data.subscribe(({group}) => {
      this.group = group;
      if (this.group.studentUuids?.length) {
        // todo: fix it
        const observableStudents$: ObservableInput<any>[] = [];
        this.group.studentUuids.forEach(uuid => {
          observableStudents$.push(this.studentService.getByUuid(uuid));
        });
        forkJoin(observableStudents$).subscribe((students: Student[]) => {
          this.students = students;
          this.isLoading = false;
        });
      } else {
        this.isLoading = false;
      }
      this.createStudentsForSelect();
    });

  }

  save() {
    this.studentGroupService.save(this.group).subscribe(() => this.router.navigate(['student-groups']))
  }

  createStudentsForSelect() {
    this.studentService.getAll().subscribe((studentRes: HttpResponse<Student[]>) => {
      studentRes.body!.forEach(student => {
        this.studentsForSelect.push({name: student.lastName + " " + student.firstName, code: student.uuid})
      })
    });
  }
}
