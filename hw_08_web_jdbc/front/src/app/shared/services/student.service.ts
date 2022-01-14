import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AbstractService} from "./abstract.service";
import {Student} from "../models/student.model";

@Injectable({
  providedIn: 'root'
})
export class StudentService extends AbstractService<Student> {

  override resourceUrl = '/api/students';

  constructor(public override httpClient: HttpClient) {
    super(httpClient);
  }
}
