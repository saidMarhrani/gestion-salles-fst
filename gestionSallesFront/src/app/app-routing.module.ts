import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RoomsComponent } from './rooms/rooms.component';
import { RoomComponent } from './rooms/room/room.component';
import { LoginComponent } from './login/login.component';
import { ReservationsComponent } from './reservations/reservations.component';
import { AuthGard } from './helpers/gard/auth.gard';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { HomeComponent } from './home/home.component';
import { StudentComponent } from './student/student.component';
import { AuthenticatedStudentComponent } from './login/authenticated-student/authenticated-student.component';
import { CreateReservationComponent } from './reservations/create-reservation/create-reservation.component';

const routes: Routes = [

  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent, canActivate: [AuthGard] },
  { path: 'login', component: LoginComponent },
  { path: 'rooms', component: RoomsComponent, canActivate: [AuthGard] },
  { path: 'rooms/:name', component: RoomComponent, canActivate: [AuthGard] },
  { path: 'reservations', component: ReservationsComponent, canActivate: [AuthGard] },
  { path: 'new-reservation', component: CreateReservationComponent, canActivate: [AuthGard] },
  { path: 'students', component: StudentComponent, canActivate: [AuthGard] },
  { path: ':username', component: AuthenticatedStudentComponent, canActivate: [AuthGard] },
  { path: '**', component: PageNotFoundComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
