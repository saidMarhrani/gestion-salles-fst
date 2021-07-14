package org.mql.gestionsalle.backend.services;

import org.mql.gestionsalle.backend.entities.Room;

import java.util.List;

public interface RoomService {

	List<Room> getAllRooms();

	List<Room> findByHasProjectorTrue();

	List<Room> findByNameNotIn(List<String> names);

	List<Room> findByNameNotIn(List<String> names, String department);

	List<String> getRoomNamesThatHadReserved();

	Room getRoom(String name);

	Room save(Room salle);

	Room update(String name, Room salle);

	void delete(String name);

	List<String> getDepartments();

}
