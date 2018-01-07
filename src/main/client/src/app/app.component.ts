import { Component, OnInit } from '@angular/core';
import { OktaAuthService } from '@okta/okta-angular';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  public title = 'My Notes';
  public user;

  constructor(public oktaService: OktaAuthService) {
  }

  ngOnInit() {
    if (this.oktaService.isAuthenticated()) {
      this.user = this.oktaService.getIdToken;
    }
  }
}
