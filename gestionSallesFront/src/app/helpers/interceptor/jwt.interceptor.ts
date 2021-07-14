import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { AuthenticationService } from "src/app/login/service/authentication.service";

@Injectable()
export class JwtInterceptor implements HttpInterceptor{

    constructor(private authenticationService: AuthenticationService){ }
    
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        const headersConfig = {};
        const currentStudentToken = this.authenticationService.currentStudentToken;

        if(currentStudentToken){
            headersConfig['Authorization'] = `Bearer ${currentStudentToken}`;
        }
        req = req.clone({
            setHeaders: headersConfig
        })
        return next.handle(req);
    }

}