package org.mql.gestionsalle.backend.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.mql.gestionsalle.backend.entities.Reservation;
import org.mql.gestionsalle.backend.enums.ReservedBy;

public interface ReservationService {


	List<Reservation> getAllReservations();

	List<Reservation> findByReservedBy(ReservedBy reservedBy);

	Reservation getReservation(long id);

	Reservation save(Reservation reservation);

	Reservation save(Reservation reservation, String cne, String roomName);

	Reservation save(Reservation reservation, String roomName);

	Reservation update(Long id, Reservation reservation, String studentCne, String roomName);

	void delete(Long id);

	List<Reservation> findByReserveDate(LocalDate date);

	List<Reservation> betweenTimes(LocalDate date, LocalTime time);

	List<Reservation> reservationsByDateAndTime(LocalDate date, LocalTime time);

	List<Reservation> findByStudentUsername(String username);

}
