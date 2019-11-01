import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

// Keep imports alphabetical for source control
import { ApiService } from './backend-api.service';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CourseFormComponent } from './course-form/course-form.component';
import { CourseListComponent } from './course-list/course-list.component';
import { DiffMatchPatchModule } from 'ng-diff-match-patch';
import { EditFormComponent } from './edit-form/edit-form.component';
import { FormsModule } from '@angular/forms';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { NgxFileDropModule } from 'ngx-file-drop';
import { PdfViewerModule } from 'ng2-pdf-viewer';
import { ReactiveFormsModule } from '@angular/forms';
import { SearchPageComponent } from './search-page/search-page.component';
import { SupportDocumentComponent } from './support-documents/support-documents.component';
import { ApprovalPipelineComponent } from './approval-pipeline/approval-pipeline.component';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatListModule } from '@angular/material/list';




@NgModule({
  declarations: [
    AppComponent,
    SearchPageComponent,
    SupportDocumentComponent,
    EditFormComponent,
    CourseFormComponent,
    CourseListComponent,
    SearchPageComponent,
    ApprovalPipelineComponent
  ],
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
    MatCheckboxModule,
    MatListModule
  ],
  providers: [ApiService],
  bootstrap: [AppComponent]
})
export class AppModule { }
