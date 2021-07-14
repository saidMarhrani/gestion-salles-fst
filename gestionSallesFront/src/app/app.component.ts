import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from './login/service/authentication.service';
import { LoginLogoutService } from './utils/login-logout.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  isLoggedIn: boolean = false;

  constructor(public loginLogoutService: LoginLogoutService, private authenticationService: AuthenticationService) { }

  ngOnInit(): void {
    if(this.authenticationService.currentStudentToken){
      this.loginLogoutService.setLoggedStatus = true;      
    }
  }
  
}
