package org.mql.gestionsalle.backend.controllers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.mql.gestionsalle.backend.entities.Reservation;
import org.mql.gestionsalle.backend.entities.Room;
import org.mql.gestionsalle.backend.services.ReservationService;
import org.mql.gestionsalle.backend.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/rooms")
@RequiredArgsConstructor
public class RoomController {

	private final RoomService roomService;
	private final ReservationService reservationService;

	@GetMapping
	public List<Room> getAllRooms() {
		return this.roomService.getAllRooms();
	}

	@GetMapping(path = "/{name}")
	public ResponseEntity<Room> getRoom(@PathVariable(name = "name") String name) {
		Room room = this.roomService.getRoom(name);
		return ResponseEntity.ok().body(room);
	}

	@GetMapping("/available")
	public List<Room> getAvailableRooms(@RequestParam(name = "department", required = false)  String department) {

		return this.roomService.findByNameNotIn(this.roomService.getRoomNamesThatHadReserved(), department);
	}

	@GetMapping("/reserved")
	public List<Room> getReservedRooms() {
		return getReservedRoomsList();
	}

	@GetMapping("/departments")
	public List<String> getDepartments() {
		return this.roomService.getDepartments();
	}

	@GetMapping("/hasProjector")
	public List<Room> roomsHasProjector() {
		return this.roomService.findByHasProjectorTrue();
	}

	// Accessible seulement par le responsable.
	@PutMapping(path = "/{name}")
	public ResponseEntity<Room> update(@PathVariable(name = "name") String name, @Valid @RequestBody Room salle) {

		Room updatedRoom = roomService.update(name, salle);

		return ResponseEntity.ok(updatedRoom);
	}

	// Accessible seulement par le responsable.
	@DeleteMapping(path = "/{name}")
	public Map<String, Boolean> delete(@PathVariable(name = "name") String name) {

		this.roomService.delete(name);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("Delete", Boolean.TRUE);

		return response;
	}

	private List<Room> getReservedRoomsList() {

		List<Reservation> allReservations = this.reservationService.getAllReservations();
		List<Room> rooms = new LinkedList<Room>();

		for (Reservation reservation : allReservations) {
			rooms.add(reservation.getRoom());
		}

		return rooms;
	}
}
