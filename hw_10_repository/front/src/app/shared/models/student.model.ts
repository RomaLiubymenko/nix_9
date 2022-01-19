import {AbstractModel} from "./abstract.model";
import {StudentGroup} from "./student-group.model";

export interface IStudent extends AbstractModel {
  firstName?: string;
  lastName?: string;
  birthDay?: Date;
  studentGroups?: StudentGroup[];
}

export class Student implements IStudent {

  constructor(
    public uuid?: string,
    public created?: string,
    public updated?: string,
    public firstName?: string,
    public lastName?: string,
    public birthDay?: Date,
    public studentGroups?: StudentGroup[]) {
  }
}
