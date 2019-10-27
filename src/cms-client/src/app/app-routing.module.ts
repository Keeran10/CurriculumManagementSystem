import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EditFormComponent } from './edit-form/edit-form.component';
import { SearchPageComponent } from './search-page/search-page.component';
import { CommonModule } from '@angular/common';
import { CourseListComponent } from './course-list/course-list.component';
import { CourseFormComponent } from './course-form/course-form.component';

const routes: Routes = [
  { path: 'editForm/:id', component: EditFormComponent },
  { path: 'searchPage', component: SearchPageComponent },
  { path: 'addcourse', component: CourseFormComponent }
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
