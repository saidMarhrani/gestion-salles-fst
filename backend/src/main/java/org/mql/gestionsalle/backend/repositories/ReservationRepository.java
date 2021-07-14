package org.mql.gestionsalle.backend.repositories;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.mql.gestionsalle.backend.entities.Reservation;
import org.mql.gestionsalle.backend.enums.ReservedBy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	List<Reservation> findByReservedBy(ReservedBy reservedBy);

	List<Reservation> findByRoomNameNotIn(List<String> names);

	List<Reservation> findByReserveDate(LocalDate date);

	@Query(value = "SELECT r FROM Reservation r WHERE r.reserveDate = :date AND :time BETWEEN r.beginningHoure AND r.endTime")
	List<Reservation> betweenTimes(@Param(value = "date") LocalDate date, @Param(value = "time") LocalTime time);

	List<Reservation> findByStudentUsername(String username);

}
