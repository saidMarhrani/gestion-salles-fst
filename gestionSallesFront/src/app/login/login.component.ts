import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginLogoutService } from '../utils/login-logout.service';
import { AuthenticationService } from './service/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  errorMessage: string = undefined;

  constructor(private authenticationService: AuthenticationService, private router: Router, 
    private formBuilder: FormBuilder, private route: ActivatedRoute, 
    private loginLogoutService: LoginLogoutService) {

    if (this.authenticationService.currentStudentToken) {
      this.router.navigate(['/']);
    }
  }

  ngOnInit(): void {
    
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });

    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    
  }

  get f() { return this.loginForm.controls; }

  onSubmit() {
    this.submitted = true;

    if (this.loginForm.invalid) {
      return;
    }

    this.loading = true;
    console.log(this.f.username.value + " " + this.f.password.value);
    this.authenticationService.login(this.f.username.value, this.f.password.value)
      .subscribe(
        data => {
          this.loginLogoutService.loggedIn();
          this.router.navigate(['/']);
        },
        err => {
          // this.error = JSON.parse(err.error);
          this.errorMessage = "Student credentials does not matches";
          this.loading = false;
        });
  }

}
