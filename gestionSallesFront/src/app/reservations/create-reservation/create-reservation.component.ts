import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/login/service/authentication.service';
import { ReservationModule } from 'src/app/modules/reservation.module';
import { RoomModule } from 'src/app/modules/Room.module';
import { RoomService } from 'src/app/rooms/service/room.service';
import { ReservationService } from '../service/reservation.service';

@Component({
  selector: 'app-create-reservation',
  templateUrl: './create-reservation.component.html',
  styleUrls: ['./create-reservation.component.css']
})
export class CreateReservationComponent implements OnInit {

  registerForm: FormGroup;
  submitted = false;
  nowDate : Date = new Date();
  departments: string[] = null;

  reservation: ReservationModule = new ReservationModule();
  rooms: RoomModule[] = [];
  roomName: string;
  successMessage: string;

  constructor(
    private roomService: RoomService, 
    private reservationService: ReservationService,
    private authenticationService: AuthenticationService,
    private router: Router,
    private formBuilder: FormBuilder
    ) { }

  ngOnInit(): void {

    this.generateFormGroup();
    this.loadAvailableRooms();
    this.loadDepartments();

  }

  private generateFormGroup() {
    this.registerForm = this.formBuilder.group({
      roomName: ['', Validators.required],
      departmentName: ['', Validators.required],
      reservedAt: ['', Validators.required],
      beginningHoure: ['', Validators.required],
      endTime: ['', Validators.required]
    });
  }

  public get f() { return this.registerForm.controls; }

  loadAvailableRooms() {
    this.roomService.getAll('available').subscribe(dataRooms => {
      this.rooms = dataRooms;
    }, err => console.log(err));
  }

  loadDepartments(){
    this.roomService.getAllDepartments().subscribe(departments =>{
      this.departments = departments;
    }, error => console.log(error));
  }

  loadRooms(){
    this.roomService.getRoomsOfParticularDepartment(this.registerForm.value.departmentName).subscribe(rooms => {
      this.rooms = rooms;
    }, error => console.log(error));
  }
  
  onValidReservation() {

    this.submitted = true;

    if (this.registerForm.invalid) {
      return;
    }

    this.roomName = this.registerForm.value.roomName;

    if (confirm("Are you sure you want to reserve this room : " + this.roomName)) {

      this.reservation = this.registerForm.value;
      this.reservation.isConfirmed = false;
      this.reservation.reserveDate = new Date();

      this.reservation.reservedBy = "STUDENT";

       this.authenticationService.findUser().subscribe(s => {
        this.reservation.student = s;
        // console.log(this.reservation);

        this.reservationService.saveReservation(this.reservation, this.roomName).subscribe(reservationObj => {
          this.successMessage = "Your reservation is saved with success";
          setTimeout(() => {
            this.router.navigate(['/reservations']);
          }, 4000);
        }, err => {
          console.log(err);
        });

      }, err => {
        console.log(err);
      });
    }

  }

}

