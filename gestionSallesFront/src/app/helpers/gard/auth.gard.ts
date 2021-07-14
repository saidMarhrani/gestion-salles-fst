import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthenticationService } from 'src/app/login/service/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGard implements CanActivate{

  constructor(private router: Router, private authenticationService: AuthenticationService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {

    const currentStudent = this.authenticationService.currentStudentToken;
    if(currentStudent){
      return true;
    }

    this.router.navigate(['/login']);
    return false;
  }
}
