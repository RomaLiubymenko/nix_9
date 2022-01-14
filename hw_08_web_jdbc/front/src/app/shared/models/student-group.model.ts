import {AbstractModel} from "./abstract.model";

export interface IStudentGroup extends AbstractModel {
  name?: string;
  description?: string;
  groupType?: string;
  studentUuids?: string[]
}

export class StudentGroup implements IStudentGroup {
  constructor(
    public uuid?: string,
    public created?: string,
    public updated?: string,
    public name?: string,
    public description?: string,
    public groupType?: GroupType,
    public studentUuids?: string[]) {
  }
}

export enum GroupType {
  INDIVIDUAL = 'INDIVIDUAL', MINI_GROUP = 'MINI_GROUP', GROUP = 'GROUP'
}


