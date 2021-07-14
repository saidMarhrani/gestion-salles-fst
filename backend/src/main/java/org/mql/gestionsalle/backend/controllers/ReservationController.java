package org.mql.gestionsalle.backend.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.validation.Valid;

import org.mql.gestionsalle.backend.entities.Reservation;
import org.mql.gestionsalle.backend.services.ReservationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/reservations")
public class ReservationController {

	private final ReservationService reservationService;

	public ReservationController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@GetMapping
	public List<Reservation> getAllReservations() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();
			return this.reservationService.findByStudentUsername(currentUserName);
		}
		return this.reservationService.getAllReservations();
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Reservation> getReservation(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok().body(this.reservationService.getReservation(id));
	}

	@GetMapping("/filterByDate")
	public List<Reservation> getReservationsByDate(
			@RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
			@RequestParam(name = "time", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime time) {

		return this.reservationService.reservationsByDateAndTime(date, time);
	}

	@PostMapping
	public ResponseEntity<Reservation> save(@Valid @RequestBody Reservation reservation, @RequestParam String roomName) {

		Reservation saveReservation = this.reservationService.save(reservation, roomName);
		return ResponseEntity.ok(saveReservation);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Reservation> update(@PathVariable Long id, @Valid @RequestBody Reservation reservation,
			@RequestParam String cne, @RequestParam String roomName) {

		Reservation saveReservation = this.reservationService.update(id, reservation, cne, roomName);
		return ResponseEntity.ok(saveReservation);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {

		this.reservationService.delete(id);
		return ResponseEntity.status(204).build();
	}

}
