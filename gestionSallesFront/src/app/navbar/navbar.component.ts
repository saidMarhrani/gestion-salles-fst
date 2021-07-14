import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../login/service/authentication.service';
import { StudentModule } from '../modules/student.module';
import { LoginLogoutService } from '../utils/login-logout.service';
import { NavbarService } from './service/navbar.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  username: string;
  
  constructor(private authenticationService: AuthenticationService, 
    private router: Router, 
    private loginLogoutService: LoginLogoutService,
    private navbarService: NavbarService) { }

  ngOnInit(): void {

    this.navbarService.findUser().subscribe(student =>{
      this.username = student.username;
    }, err =>{
      console.log(err);
    })
  }

  logout() {
    this.authenticationService.logout();
    this.loginLogoutService.loggedOut();
    this.router.navigate(['/login']);
  }

}
