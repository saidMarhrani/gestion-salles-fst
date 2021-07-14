import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { RoomsComponent } from './rooms/rooms.component';
import { RoomComponent } from './rooms/room/room.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './login/login.component';
import { NavbarComponent } from './navbar/navbar.component';
import { ReservationsComponent } from './reservations/reservations.component';
import { JwtInterceptor } from './helpers/interceptor/jwt.interceptor';
import { ErrorInterceptor } from './helpers/error/error.interceptor';
import { HomeComponent } from './home/home.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { StudentComponent } from './student/student.component';
import { AuthenticatedStudentComponent } from './login/authenticated-student/authenticated-student.component';
import { CreateReservationComponent } from './reservations/create-reservation/create-reservation.component';

@NgModule({
  declarations: [
    AppComponent,
    RoomsComponent,
    RoomComponent,
    LoginComponent,
    NavbarComponent,
    ReservationsComponent,
    HomeComponent,
    PageNotFoundComponent,
    StudentComponent,
    AuthenticatedStudentComponent,
    CreateReservationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
