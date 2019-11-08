export class CourseExtras {
  antirequisites: string;
  corequisites: string;
  equivalents: string;
  files: File[];
  implications: string;
  packageId: number;
  prerequisites: string;
  rationale: string;
  userId: number;

  constructor() {
    this.antirequisites = '';
    this.corequisites = '';
    this.equivalents = '';
    this.implications = '';
    this.packageId = 1;
    this.prerequisites = '';
    this.rationale = '';
    this.userId = 1;
  }
};