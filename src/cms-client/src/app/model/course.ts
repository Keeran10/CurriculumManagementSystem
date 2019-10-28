export class Course {
  id: number;
  antirequisites: string[];
  corequisites: string[];
  credits: number;
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
  prerequisites: string[];
  requisites: Object[];
  title: string;
  tutorialHours: number;
}
