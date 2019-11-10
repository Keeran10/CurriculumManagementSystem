import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EditFormComponent } from './edit-form/edit-form.component';
import { SearchPageComponent } from './search-page/search-page.component';
import { CommonModule } from '@angular/common';
import { CourseFormComponent } from './course-form/course-form.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
  { path: '', component: SearchPageComponent },
  { path: 'editForm/:id', component: EditFormComponent },
  { path: 'addcourse', component: CourseFormComponent },
  { path: 'login', component: LoginComponent }
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
