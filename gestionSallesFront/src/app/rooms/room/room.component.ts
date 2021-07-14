import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RoomModule } from 'src/app/modules/Room.module';
import { RoomService } from '../service/room.service';

@Component({
  selector: 'app-room',
  templateUrl: './room.component.html',
  styleUrls: ['./room.component.css']
})
export class RoomComponent implements OnInit {

  room: RoomModule = new RoomModule();
  private roomName: any;
  
  constructor(private route: ActivatedRoute, private roomService: RoomService) { }

  ngOnInit(): void {
    this.route.params.subscribe(name => {
      this.roomName = name.name
    });

    console.log(this.roomName);
    this.loadRoom(this.roomName);
  }

  loadRoom(name: string){
    
    this.roomService.getOneByName(name).subscribe(room =>{
      this.room = room;
    });
  }

}
