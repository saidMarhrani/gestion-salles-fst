import { Component, OnInit } from '@angular/core';
import {RoomService} from './service/room.service';

@Component({
  selector: 'app-rooms',
  templateUrl: './rooms.component.html',
  styleUrls: ['./rooms.component.css']
})
export class RoomsComponent implements OnInit {

  rooms: any = [];
  selectedChoice: string;

  constructor(private roomService: RoomService) { }

  ngOnInit(): void {
    this.loadData();
  }

  loadData(param?: string){
    this.roomService.getAll(param).subscribe(data =>{
      this.rooms = data;
    });
  }
  
  onChangeRooms(){
    this.loadData(this.selectedChoice);
  }

}
