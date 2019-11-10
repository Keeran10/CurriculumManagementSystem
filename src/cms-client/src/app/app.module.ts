import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

// Keep imports alphabetical for source control
import { ApiService } from './backend-api.service';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { ApprovalPipelineComponent } from './approval-pipeline/approval-pipeline.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CookieService } from 'ngx-cookie-service';
import { CourseFormComponent } from './course-form/course-form.component';
import { CourseListComponent } from './course-list/course-list.component';
import { DialogImpactStatementComponent, ImpactStatementComponent } from './impact-statement/impact-statement.component';
import { DiffMatchPatchModule } from 'ng-diff-match-patch';
import { EditFormComponent } from './edit-form/edit-form.component';
import { GenericFileUploaderComponent } from './generic-file-uploader/generic-file-uploader.component';
import { FormsModule } from '@angular/forms';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatSelectModule } from '@angular/material/select';
import { NgxFileDropModule } from 'ngx-file-drop';
import { PackageComponent } from './package/package.component';
import { PdfViewerModule } from 'ng2-pdf-viewer';
import { PipelineTrackingComponent } from './pipeline-tracking/pipeline-tracking.component';
import { ReactiveFormsModule } from '@angular/forms';
import { SearchPageComponent } from './search-page/search-page.component';
import { SupportDocumentComponent } from './support-documents/support-documents.component';
import { MatDialogModule } from '@angular/material/dialog';



@NgModule({
  declarations: [
    AppComponent,
    ApprovalPipelineComponent,
    CourseFormComponent,
    CourseListComponent,
    DialogImpactStatementComponent,
    EditFormComponent,
    ImpactStatementComponent,
    PackageComponent,
    PipelineTrackingComponent,
    SearchPageComponent,
    SupportDocumentComponent,
    GenericFileUploaderComponent
  ],
  entryComponents: [DialogImpactStatementComponent, ImpactStatementComponent],
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
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    ReactiveFormsModule,
    NgxFileDropModule,
    PdfViewerModule,
    MatDialogModule,
    MatCheckboxModule,
    MatListModule
  ],
  providers: [
    ApiService,
    CookieService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
