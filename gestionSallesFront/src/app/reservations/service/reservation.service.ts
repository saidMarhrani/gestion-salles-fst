import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, retry } from 'rxjs/operators';
import { ErrorInterceptor } from 'src/app/helpers/error/error.interceptor';
import { ReservationModule } from 'src/app/modules/reservation.module';

const URL = "http://localhost:8080/reservations"

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  constructor(private http : HttpClient) { }

  getAllReservations(){
    return this.http.get<ReservationModule[]>(URL);
  }

  saveReservation(reservation: ReservationModule, roomName: string){
    return this.http.post(URL + '?roomName=' + roomName, reservation);
  }
  
}
