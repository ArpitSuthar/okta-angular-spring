import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { OktaAuthService } from '@okta/okta-angular';
import 'rxjs/add/operator/do';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class OktaAuthInterceptor implements HttpInterceptor {

  constructor(private oktaService: OktaAuthService) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    request.headers.append('X-Requested-With', 'XMLHttpRequest');
    if (this.oktaService.isAuthenticated()) {
      const accessToken = this.oktaService.getOktaAuth().tokenManager.get('accessToken');
      request = request.clone({
        setHeaders: {
          'X-Requested-With': 'XMLHttpRequest',
          // tslint:disable-next-line:object-literal-key-quotes
          Authorization: `${accessToken.tokenType} ${accessToken.accessToken}`,
        },
      });
    }

    return next.handle(request);
  }
}
