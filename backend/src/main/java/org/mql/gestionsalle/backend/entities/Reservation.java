package org.mql.gestionsalle.backend.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.NoArgsConstructor;

@Entity
@Table(name = "RESERVATIONS")
@Getter
@Setter
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long reservationId;

//    @FutureOrPresent(message = "Date must be in the future")
    @Column(nullable = false, name = "reserved_at")
    private LocalDate reservedAt;

    @Column(name = "is_confirmed")
    private Boolean isConfirmed;

    @Column(nullable = false, name = "reservation_date")
    private LocalDate reserveDate;

    @Column(nullable = false, name = "beginning_houre")
    private LocalTime beginningHoure;

    @Column(nullable = false, name = "end_time")
    private LocalTime endTime;

    //    @Enumerated(EnumType.STRING)
    @Column(name = "reserved_by")
    private String reservedBy;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name = "student_id")
    private Student student;

    @JsonIgnore
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "room_id")
    private Room room;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "responsible_id")
    private Responsible responsible;

    public Reservation(LocalDate reserveDate, LocalDate reservedAt, LocalTime beginningHoure, LocalTime endTime,
                       String reservedBy, Student student, Room room, Responsible responsible) {
        super();
        this.reservedAt = reservedAt;
        this.reserveDate = reserveDate;
        this.beginningHoure = beginningHoure;
        this.endTime = endTime;
        this.reservedBy = reservedBy;
        this.student = student;
        this.room = room;
        this.responsible = responsible;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", reservedAt=" + reservedAt +
                ", isConfirmed=" + isConfirmed +
                ", reserveDate=" + reserveDate +
                ", beginningHoure=" + beginningHoure +
                ", endTime=" + endTime +
                ", reservedBy='" + reservedBy + '\'' +
                ", student=" + student +
                ", room=" + room +
                ", responsible=" + responsible +
                '}';
    }
}
