package org.mql.gestionsalle.backend.repositories;

import java.util.List;

import org.mql.gestionsalle.backend.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, String> {

	List<Room> findByHasProjectorTrue();

	List<Room> findByNameNotIn(List<String> names);

}
