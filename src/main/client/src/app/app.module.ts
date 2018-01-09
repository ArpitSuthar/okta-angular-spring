import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { OktaAuthGuard, OktaAuthModule, OktaAuthService } from '@okta/okta-angular';
import { AppComponent } from './app.component';

import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { OktaConfig } from '@okta/okta-angular/dist/okta/okta.config';
import { AppRoutingModule } from './app-routing.module';
import { NoteListComponent } from './note-list/note-list.component';
import { NoteService } from './shared/note/note.service';
import { OktaAuthInterceptor } from './shared/okta-auth-interceptor/okta-auth-interceptor.service';

const config: OktaConfig = {
  issuer: 'https://dev-109699.oktapreview.com/oauth2/default',
  redirectUri: 'http://localhost:8080/',
  clientId: '0oadir39hzH44rCUX0h7',
};

@NgModule({
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    OktaAuthModule.initAuth(config),
  ],
  declarations: [
    AppComponent,
    NoteListComponent,
  ],
  bootstrap: [ AppComponent ],
  providers: [NoteService, {
    provide: HTTP_INTERCEPTORS,
    useClass: OktaAuthInterceptor,
    multi: true,
  }],
})
export class AppModule { }
