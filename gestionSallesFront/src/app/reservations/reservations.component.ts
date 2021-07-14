import { Component, OnInit } from '@angular/core';
import { ReservationModule } from '../modules/reservation.module';
import { ReservationService } from './service/reservation.service';

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.css']
})
export class ReservationsComponent implements OnInit {

  reservations: ReservationModule[];
  
  constructor(private reservationService: ReservationService) { }

  ngOnInit(): void {
    this.loadData();
  }

  loadData(){
    this.reservationService.getAllReservations().subscribe(data=> {
      this.reservations = data;
    }, error =>{
      console.log(error);
    });
  }

}
