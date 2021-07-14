import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { AuthenticationService } from 'src/app/login/service/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class ErrorInterceptor implements HttpInterceptor {

  constructor(private authenticationService: AuthenticationService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    return next.handle(req).pipe(catchError(err => {
      if(err.status === 403 || err.status === 401){
        console.log("hello ........... " + err);
        this.authenticationService.logout();
        location.reload();
      }

      if(err.error.message !== undefined){
        // const error = err.error.message || err['statusText'];
        alert(`${err.error.message}`);
      }
      return throwError(err);
    }));
    
  }
}
