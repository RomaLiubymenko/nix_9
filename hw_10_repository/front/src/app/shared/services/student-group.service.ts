import {Injectable} from '@angular/core';
import {AbstractService} from "./abstract.service";
import {HttpClient} from "@angular/common/http";
import {StudentGroup} from "../models/student-group.model";

@Injectable({
  providedIn: 'root'
})
export class StudentGroupService extends AbstractService<StudentGroup> {

  override resourceUrl = '/api/student-groups';

  constructor(public override httpClient: HttpClient) {
    super(httpClient);
  }
}
