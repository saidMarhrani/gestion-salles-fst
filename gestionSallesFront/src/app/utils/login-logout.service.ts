import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
})
export class LoginLogoutService{

    private isLoggedIn: boolean = false;

    constructor(){ }

    public get getLoggedStatus(){
        return this.isLoggedIn;
    }

    public set setLoggedStatus(status: boolean){
        this.isLoggedIn = status;
    }

    loggedOut(){
        this.isLoggedIn = false;
    }

    loggedIn(){
        this.isLoggedIn = true;
    }
    
}