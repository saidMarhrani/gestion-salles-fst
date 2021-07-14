import { Injectable } from "@angular/core";
import { AuthenticationService } from "src/app/login/service/authentication.service";
import { JwtHelperService } from '@auth0/angular-jwt';
import { HttpClient } from "@angular/common/http";
import { StudentModule } from "src/app/modules/student.module";

const URL = 'http://localhost:8080/students/'

@Injectable({
    providedIn: 'root'
})
export class NavbarService{

    constructor(private authenticationService: AuthenticationService){ }
    
    findUser(){
        return this.authenticationService.findUser();
    }
    
}