import { Requisite } from '../model/requisite';

export class Course {
  id: number;
  credits: number;
  degreeRequirements: Object[];
  description: string;
  equivalent: string[];
  isActive: boolean;
  labHours: number;
  lectureHours: number;
  level: number;
  name: string;
  note: string;
  number: number;
  outline: string;
  program: Object;
  requisites: Requisite[];
  title: string;
  tutorialHours: number;
}
