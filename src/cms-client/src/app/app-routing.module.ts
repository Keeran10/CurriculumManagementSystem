import { CommonModule } from '@angular/common';
import { CourseFormComponent } from './course-form/course-form.component';
import { EditFormComponent } from './edit-form/edit-form.component';
import { NgModule } from '@angular/core';
import { PackageComponent } from './package/package.component';
import { PipelineTrackingComponent } from './pipeline-tracking/pipeline-tracking.component';
import { RouterModule, Routes } from '@angular/router';
import { SearchPageComponent } from './search-page/search-page.component';

const routes: Routes = [
  { path: 'addcourse', component: CourseFormComponent },
  { path: 'search', component: SearchPageComponent },
  { path: 'editForm/:id', component: EditFormComponent },
  { path: 'pipeline', component: PipelineTrackingComponent },
  { path: '', component: PackageComponent }
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
