package org.mql.gestionsalle.backend.services.load;

import org.mql.gestionsalle.backend.entities.*;
import org.mql.gestionsalle.backend.enums.Department;
import org.mql.gestionsalle.backend.enums.ReservedBy;
import org.mql.gestionsalle.backend.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class LoadData implements CommandLineRunner {

    private final StudentService studentService;

    private final ReservationService reservationService;

    private final FiliereService filiereService;

    private final RoomService roomService;

    private final ResponsibleService responsibleService;


    public LoadData(StudentService studentService, ReservationService reservationService, FiliereService filiereService, RoomService roomService, ResponsibleService responsibleService) {
        this.studentService = studentService;
        this.reservationService = reservationService;
        this.filiereService = filiereService;
        this.roomService = roomService;
        this.responsibleService = responsibleService;
    }

    @Override
    public void run(String... args) {
        Filiere mql = filiereService.save(new Filiere("MQL", "2ème annèe"));

        Student student = this.studentService.save(
                new Student("cne456", "marhrani", "said", "said_marhrani","78544", "said@gmail.com", "said123", mql));

        this.responsibleService
                .save(new Responsible("ahmed mohammed", "ahmed@gmail.com", "123", "Tranc commun Scolarité"));
        Responsible responsible = this.responsibleService.getResponsible();

        Room room = new Room("Salle C1", Department.CHEMISTRY.toString(), false, false);
        Room room2 = new Room("Salle P1", Department.PHYSICS.toString(), false, false);
        Room room3 = new Room("Salle INFO 1", Department.INFORMATIC.toString(), false, false);
        Room room4 = new Room("Salle INFO 2", Department.INFORMATIC.toString(), false, false);
        Room room5 = new Room("Salle INFO 3", Department.INFORMATIC.toString(), true, false);

        this.roomService.save(room2);
        this.roomService.save(room3);
        this.roomService.save(room4);
        this.roomService.save(room5);

        Reservation reservation = new Reservation(LocalDate.now(), LocalDate.of(2021, 9, 25), LocalTime.of(10, 00),
                LocalTime.of(12, 30), ReservedBy.STUDENT.toString(), student, room, responsible);
        reservationService.save(reservation);

    }

}
