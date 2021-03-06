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

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

// Keep imports alphabetical for source control
import { ApiService } from './backend-api.service';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { ApprovalPipelineComponent } from './approval-pipeline/approval-pipeline.component';
import { ApproverHomepageComponent } from './approver-homepage/approver-homepage.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CalendarSectionComponent } from './calendar-section/calendar-section.component';
import { CalendarSectionsComponent } from './calendar-sections/calendar-sections.component';
import { CookieService } from 'ngx-cookie-service';
import { CourseFormComponent } from './course-form/course-form.component';
import { CourseListComponent } from './course-list/course-list.component';
import { DialogImpactStatementComponent, ImpactStatementComponent } from './impact-statement/impact-statement.component';
import { DiffMatchPatchModule } from 'ng-diff-match-patch';
import { EditFormComponent } from './edit-form/edit-form.component';
import { FooterComponent } from './footer/footer.component';
import { GenericFileUploaderComponent } from './generic-file-uploader/generic-file-uploader.component';
import { FormsModule } from '@angular/forms';
import { LoginComponent } from './login/login.component';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatMenuModule } from '@angular/material/menu';
import { MatSelectModule } from '@angular/material/select';
import { MatToolbarModule } from '@angular/material/toolbar';
import { NgxFileDropModule } from 'ngx-file-drop';
import { PackageComponent } from './package/package.component';
import { PdfViewerModule } from 'ng2-pdf-viewer';
import { PipelineTrackingComponent } from './pipeline-tracking/pipeline-tracking.component';
import { PipelineAuditComponent } from './pipeline-audit/pipeline-audit.component';
import { ReactiveFormsModule } from '@angular/forms';
import { RegistrationComponent } from './admin-registration/admin-registration.component';
import { RevisionsComponent } from './revisions/revisions.component';
import { SearchPageComponent } from './search-page/search-page.component';
import { SupportDocumentComponent } from './support-documents/support-documents.component';
import { TopNavBarComponent } from './top-nav-bar/top-nav-bar.component';
import { CalendarCourseListComponent } from './calendar-course-list/calendar-course-list.component';
import { CheckTrendsComponent, DialogCheckTrendsComponent } from './check-trends/check-trends.component';
import { MatSidenavModule } from '@angular/material/sidenav';
import { SideNavBarComponent } from './side-nav-bar/side-nav-bar.component';
import { MatBadgeModule } from '@angular/material/badge';


@NgModule({
  declarations: [
    AppComponent,
    ApprovalPipelineComponent,
    ApproverHomepageComponent,
    CalendarSectionsComponent,
    CalendarSectionComponent,
    CheckTrendsComponent,
    CourseFormComponent,
    CourseListComponent,
    DialogCheckTrendsComponent,
    DialogImpactStatementComponent,
    EditFormComponent,
    FooterComponent,
    GenericFileUploaderComponent,
    ImpactStatementComponent,
    LoginComponent,
    PackageComponent,
    PipelineTrackingComponent,
    PipelineAuditComponent,
    RevisionsComponent,
    RegistrationComponent,
    SearchPageComponent,
    SupportDocumentComponent,
    TopNavBarComponent,
    CalendarCourseListComponent,
    SideNavBarComponent
  ],
  entryComponents: [CheckTrendsComponent, DialogCheckTrendsComponent, DialogImpactStatementComponent, ImpactStatementComponent],
  exports: [MatSidenavModule],
  // Keep imports alphabetical for source control
  imports: [
    AppRoutingModule,
    BrowserAnimationsModule,
    BrowserModule,
    DiffMatchPatchModule,
    FormsModule,
    HttpClientModule,
    MatAutocompleteModule,
    MatButtonModule,
    MatCardModule,
    MatCheckboxModule,
    MatDialogModule,
    MatExpansionModule,
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
    MatListModule,
    MatMenuModule,
    MatSelectModule,
    MatToolbarModule,
    NgxFileDropModule,
    ReactiveFormsModule,
    PdfViewerModule,
    MatSidenavModule,
    MatBadgeModule,
  ],
  providers: [
    ApiService,
    CookieService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
