package org.mql.gestionsalle.backend.services.impl;

import lombok.RequiredArgsConstructor;
import org.mql.gestionsalle.backend.entities.Reservation;
import org.mql.gestionsalle.backend.entities.Responsible;
import org.mql.gestionsalle.backend.entities.Room;
import org.mql.gestionsalle.backend.enums.ReservedBy;
import org.mql.gestionsalle.backend.exceptions.ReservationCouldNotExecuted;
import org.mql.gestionsalle.backend.exceptions.ResourceCouldNotBeNullException;
import org.mql.gestionsalle.backend.exceptions.ResourceNotFoundException;
import org.mql.gestionsalle.backend.repositories.ReservationRepository;
import org.mql.gestionsalle.backend.services.ReservationService;
import org.mql.gestionsalle.backend.services.ResponsibleService;
import org.mql.gestionsalle.backend.services.RoomService;
import org.mql.gestionsalle.backend.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static java.time.temporal.ChronoUnit.MINUTES;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

	private final ReservationRepository reservationRepository;
	private final StudentService studentService;
	private final ResponsibleService responsibleService;

	@Autowired
	private RoomService roomService;

	@Override
	public List<Reservation> getAllReservations() {
		return this.reservationRepository.findAll();
	}

	@Override
	public Reservation getReservation(long id) {
		return this.reservationRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Reservation id (" + id + ") could not be found !"));
	}

	@Override
	public Reservation save(Reservation reservation) {

		if (reservation != null) {
			Room room = reservation.getRoom();
			room.setIsReserved(true);
			return this.reservationRepository.save(reservation);
		}
		throw new ResourceCouldNotBeNullException("Reservation could not be found");
	}

	@Override
	public Reservation update(Long id, Reservation reservation, String studentCne, String roomName) {

		Reservation oldReservation = getReservation(id);
		reservation.setReservationId(oldReservation.getReservationId());
		this.layingObjects(studentCne, roomName, reservation);
		Reservation reservationUpdated = formatHours(reservation);

		return this.reservationRepository.save(reservationUpdated);
	}

	@Override
	public void delete(Long id) {
		Reservation oldReservation = getReservation(id);
		this.reservationRepository.delete(oldReservation);
	}

	private void layingObjects(String studentCne, String roomName, Reservation reservation) {

		reservation.setStudent(this.studentService.getStudent(studentCne));
		reservation.setRoom(this.roomService.getRoom(roomName));
	}

	private Reservation formatHours(Reservation reservation) {

		try {
			reservation.setBeginningHoure(LocalTime.now());
			reservation.setEndTime(LocalTime.now());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return reservation;
	}

	@Override
	public List<Reservation> findByReservedBy(ReservedBy reservedBy) {
		return this.reservationRepository.findByReservedBy(reservedBy);
	}

	@Override
	public Reservation save(Reservation reservation, String cne, String roomName) {
		if (reservation != null && cne != null && roomName != null) {

			this.layingObjects(cne, roomName, reservation);
			Responsible responsible = this.responsibleService.getResponsible();
			reservation.setResponsible(responsible);

			return this.reservationRepository.save(reservation);
		}
		throw new ResourceCouldNotBeNullException("Reservation could not be null");
	}

	@Override
	public Reservation save(Reservation reservation, String roomName) {

		LocalTime beginningHour = reservation.getBeginningHoure();
		LocalTime endTime = reservation.getEndTime();
		int compareTimes = beginningHour.compareTo(endTime);
		LocalDate now = LocalDate.now();

		if(compareTimes < 0 && now.compareTo(reservation.getReservedAt()) <= 0){

			if(MINUTES.between(beginningHour, endTime) > 59){

				Responsible responsible = this.responsibleService.getResponsible();
				Room room = this.roomService.getRoom(roomName);
				room.setIsReserved(true);
				reservation.setRoom(room);
				reservation.setResponsible(responsible);

				return this.reservationRepository.save(reservation);
			}

		}

		throw new ReservationCouldNotExecuted("Check infromations, date should be in the future and end Time should be greather than beginning Houre");
	}

	@Override
	public List<Reservation> findByReserveDate(LocalDate date) {
		return this.reservationRepository.findByReserveDate(date);
	}

	@Override
	public List<Reservation> betweenTimes(LocalDate date, LocalTime time) {
		return this.reservationRepository.betweenTimes(date, time);
	}

	public List<Reservation> reservationsByDateAndTime(LocalDate date, LocalTime time) {
		if (date != null && time != null)
			return this.betweenTimes(date, time);
		else if (date != null)
			return this.findByReserveDate(date);

		throw new ResourceCouldNotBeNullException("Reservation could not be null");
	}

	@Override
	public List<Reservation> findByStudentUsername(String username) {
		return this.reservationRepository.findByStudentUsername(username);
	}

}
