import {Assessment} from "./assessment.model";

export class Subject{
  id: string;
  code:string;
  description:string;
  info: string;
  active:boolean;
  assessmentList : Assessment [];
}
