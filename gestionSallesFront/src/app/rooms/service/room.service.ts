import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RoomModule } from '../../modules/Room.module';

const URL = 'http://localhost:8080/rooms';

@Injectable({
  providedIn: 'root'
})

export class RoomService {

  constructor(private http: HttpClient) { }

  // tslint:disable-next-line:typedef
  getAll(selectedChoice?: string) {

    if (selectedChoice === 'available')
      return this.http.get<RoomModule[]>(URL + '/available');

    else if (selectedChoice === 'reserved')
      return this.http.get<RoomModule[]>(URL + '/reserved');

    else if (selectedChoice === 'hasProjector')
      return this.http.get<RoomModule[]>(URL + '/hasProjector');

    return this.http.get<RoomModule[]>(URL);
  }

  
  getAllDepartments(){
    return this.http.get<string[]>(URL+ '/departments'); 
  }

  getRoomsOfParticularDepartment(department: string){
    return this.http.get<RoomModule[]>(URL + '/available?department=' + department);
  }
  
  // tslint:disable-next-line:typedef
  getOneByName(name: string) {
    return this.http.get<RoomModule>(URL + '/' + name);
  }
}
