import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { OktaAuthGuard } from '@okta/okta-angular';
import { LoginComponent } from './login/login.component';
import { NoteListComponent } from './note-list/note-list.component';

const appRoutes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'notes', component: NoteListComponent, canActivate: [OktaAuthGuard]},
  {path: '', redirectTo: '/notes', pathMatch: 'full'},
];

@NgModule({
  imports: [ RouterModule.forRoot(appRoutes) ],
  exports: [ RouterModule ],
})
export class AppRoutingModule {}
