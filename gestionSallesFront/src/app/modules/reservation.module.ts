import { Time } from "@angular/common";
import { StudentModule } from "./student.module";

export class ReservationModule{

    reservationId: number;
    reservedAt: Date;
    isConfirmed: boolean;
    reserveDate: Date;
    beginningHoure: Time;
    endTime: Time;
    reservedBy: string;
    student: StudentModule = new StudentModule();

}