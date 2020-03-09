// MIT License

// Copyright (c) 2019 teamCMS

// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:

// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.

// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.
import { Course } from './course';
export class Section {
  id: number;
  sectionId: number;
  sectionTitle: string;
  firstParagraph: string;

  firstCore: string;
  secondCore: string;
  thirdCore: string;
  secondParagraph: string;
  fourthCore: string;
  thirdParagraph: string;
  fifthCore: string;
  fourthParagraph: string;
  sixthCore: string;
  fifthParagraph: string;
  seventhCore: string;
  sixthParagraph: string;
  eightCore: string;
  seventhParagraph: string;
  ninthCore: string;
  eightParagraph: string;
  tenthCore: string;
  ninthParagraph: string;
  eleventhCore: string;
  tenthParagraph: string;
  twelfthCore: string;
  eleventhParagraph: string;
  thirteenthCore: string;
  twelfthParagraph: string;
  fourteenthCore: string;
  fifteenthCore: string;
  thirteenthParagraph: string;
  sixteenthCore: string;
  fourteenthParagraph: string;
  seventeenthCore: string;
  fifteenthParagraph: string;
  eighteenthCore: string;
  sixteenthParagraph: string;
  nineteenthCore: string;
  seventeenthParagraph: string;

  firstCoreCourses: Course[];
  secondCoreCourses: Course[];
  thirdCoreCourses: Course[];
  fourthCoreCourses: Course[];
  fifthCoreCourses: Course[];
  sixthCoreCourses: Course[];
  seventhCoreCourses: Course[];
  eightCoreCourses: Course[];

  // mech
  introParagraph: string;
  electivesHeader: string;
  electivesDescription: string;
  firstOption: string;
  secondOption: string;
  thirdOption: string;
  fourthOption: string;
  fifthOption: string;
  sixthOption: string;
  firstOptionCourses: Course[];
  sectionOptionCourses: Course[];
  thirdOptionCourses: Course[];
  fourthOptionCourses: Course[];
  fifthOptionCourses: Course[];
  sixthOptionCourses: Course[];
  // indu
  scienceCore: string;
  scienceCoreCourses: Course[];
  electiveCourses: Course[];
}
