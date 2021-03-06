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

import { ApprovalPipelineComponent } from './approval-pipeline/approval-pipeline.component';
import { ApproverHomepageComponent } from './approver-homepage/approver-homepage.component';
import { CommonModule } from '@angular/common';
import { CourseFormComponent } from './course-form/course-form.component';
import { EditFormComponent } from './edit-form/edit-form.component';
import { LoginComponent } from './login/login.component';
import { NgModule } from '@angular/core';
import { PackageComponent } from './package/package.component';
import { PipelineAuditComponent } from './pipeline-audit/pipeline-audit.component';
import { PipelineTrackingComponent } from './pipeline-tracking/pipeline-tracking.component';
import { RouterModule, Routes } from '@angular/router';
import { SearchPageComponent } from './search-page/search-page.component';
import { RevisionsComponent } from './revisions/revisions.component';
import {CalendarSectionComponent} from './calendar-section/calendar-section.component';
import { CalendarSectionsComponent } from './calendar-sections/calendar-sections.component';
import { RegistrationComponent } from './admin-registration/admin-registration.component';
import { CalendarCourseListComponent } from './calendar-course-list/calendar-course-list.component';

const routes: Routes = [
  { path: 'addcourse', component: CourseFormComponent },
  { path: 'approval', component: ApprovalPipelineComponent},
  { path: 'search', component: SearchPageComponent },
  { path: 'editForm/:id', component: EditFormComponent },
  { path: 'homepage', component: ApproverHomepageComponent },
  { path: 'pipeline', component: PipelineTrackingComponent },
  { path: 'package', component: PackageComponent },
  { path: 'revisions', component: RevisionsComponent},
  { path: 'audit', component: PipelineAuditComponent},
  { path: 'section/:id', component: CalendarSectionComponent},
  { path: 'calendar', component: CalendarSectionsComponent },
  { path: 'registration', component: RegistrationComponent},
  { path: 'tempPage', component: CalendarCourseListComponent },
  { path: '', component: LoginComponent }
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }
