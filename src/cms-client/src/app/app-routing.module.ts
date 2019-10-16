import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EditFormComponent } from './edit-form/edit-form.component';
import { SearchPageComponent } from './search-page/search-page.component';

const routes: Routes = [
  {path: 'editForm', component: EditFormComponent},
  {path: 'searchPage', component: SearchPageComponent}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
