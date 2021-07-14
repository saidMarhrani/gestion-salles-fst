package org.mql.gestionsalle.backend.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.mql.gestionsalle.backend.entities.Room;
import org.mql.gestionsalle.backend.enums.Department;
import org.mql.gestionsalle.backend.exceptions.ResourceCouldNotBeNullException;
import org.mql.gestionsalle.backend.exceptions.ResourceNotFoundException;
import org.mql.gestionsalle.backend.repositories.RoomRepository;
import org.mql.gestionsalle.backend.services.ReservationService;
import org.mql.gestionsalle.backend.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final ReservationService reservationService;

    @Override
    public List<Room> getAllRooms() {

        return this.roomRepository.findAll();
    }

    @Override
    public Room getRoom(String id) {
        return this.roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room that has " + id + ", does not found !!"));
    }

    @Override
    public Room save(Room salle) {
        if (salle != null)
            return this.roomRepository.save(salle);
        throw new ResourceCouldNotBeNullException("Room could not be null !!");
    }

    @Override
    public Room update(String id, Room salle) {
        Room oldRoom = getRoom(id);
        salle.setName(oldRoom.getName());
        return this.roomRepository.save(salle);
    }

    @Override
    public void delete(String id) {
        Room oldRoom = getRoom(id);
        this.roomRepository.delete(oldRoom);
    }

    @Override
    public List<String> getDepartments() {
        return Department.departmentList();
    }

    @Override
    public List<Room> findByHasProjectorTrue() {
        return this.roomRepository.findByHasProjectorTrue();
    }

    @Override
    public List<Room> findByNameNotIn(List<String> names) {
        return this.roomRepository.findByNameNotIn(names);
    }

    @Override
    public List<Room> findByNameNotIn(List<String> names, String department) {
        if (department != null) {
            String dep = Department.findDepartment(department.toLowerCase());
            List<Room> roomList = this.roomRepository.findByNameNotIn(names).stream().filter(room -> room.getDepartment().toString().toLowerCase().equals(dep)).collect(Collectors.toList());
            return roomList;
        }
        return findByNameNotIn(names);
    }

    public List<String> getRoomNamesThatHadReserved() {
        return this.reservationService.getAllReservations().stream().map(r -> r.getRoom().getName())
                .collect(Collectors.toList());
    }

}
