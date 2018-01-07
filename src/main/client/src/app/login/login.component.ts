import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OktaAuthService } from '@okta/okta-angular';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {

  constructor(public oktaService: OktaAuthService,
              private router: Router) { }

  ngOnInit() {
    if (this.oktaService.isAuthenticated()) {
      this.router.navigate(['/']);
    }
  }

}
