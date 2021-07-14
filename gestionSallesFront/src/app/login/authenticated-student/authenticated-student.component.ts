import { Component, OnInit } from '@angular/core';
import { StudentModule } from 'src/app/modules/student.module';
import { AuthenticationService } from '../service/authentication.service';

@Component({
  selector: 'app-authenticated-student',
  templateUrl: './authenticated-student.component.html',
  styleUrls: ['./authenticated-student.component.css']
})
export class AuthenticatedStudentComponent implements OnInit {

  student: StudentModule;
  
  constructor(private authenticationService: AuthenticationService) { }

  ngOnInit(): void {
    this.authenticationService.findUser().subscribe(student =>{
      this.student = student;
    }, err => {
      console.log(err);
    })
  }

}
