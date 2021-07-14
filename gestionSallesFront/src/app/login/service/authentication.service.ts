import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { JwtHelperService } from '@auth0/angular-jwt';
import { StudentModule } from "src/app/modules/student.module";

const STUDENTS_URL = 'http://localhost:8080/students/'

const AUTHENTICATE_URL = "http://localhost:8080/authenticate";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private token: any = null;
  private username: string;

  constructor(private http: HttpClient) {
  }

  public get currentStudentToken(): any {
    return localStorage.getItem('studentToken');
  }

  login(username: string, password: string) {

    return this.http.post<any>(AUTHENTICATE_URL, { username, password }, { responseType: 'text' as 'json' })
      .pipe(map(studentToken => {

        localStorage.setItem('studentToken', studentToken);
        return studentToken;
      }));;

  }

  logout() {
    localStorage.removeItem('studentToken');
    this.token = null;
  }

  findUser() {
    const tokenObj = this.extractToken();
    this.username = tokenObj.sub;
    return this.http.get<StudentModule>(STUDENTS_URL + this.username);
  }

  private extractToken() {
    const helper = new JwtHelperService();
    const decodedToken = helper.decodeToken(this.currentStudentToken);
    return decodedToken;
  }

}
