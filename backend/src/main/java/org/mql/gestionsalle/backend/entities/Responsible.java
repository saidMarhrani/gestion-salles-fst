package org.mql.gestionsalle.backend.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "responsibles")
@Data
@NoArgsConstructor
public class Responsible {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "responsible_id")
	private Long responsibleId;

	@Column(nullable = false, name = "fullname")
	private String name;

	@Column(nullable = false, unique = true, name = "email")
	private String email;

	@Column(nullable = false, name = "password")
	private String password;

	@Column(nullable = false, name = "office")
	private String office;

	@OneToMany(mappedBy = "responsible")
	private List<Reservation> reservations = new ArrayList<>();

//	@OneToMany
//	private List<Room> rooms = new ArrayList<>();

	public void addReservations(Reservation reservation) {
		if (!reservations.contains(reservation))
			reservations.add(reservation);
	}

//	public void addRoom(Room room) {
//		if (!rooms.contains(room))
//			rooms.add(room);
//	}

	public Responsible(String nom, String email, String password, String bureau) {
		super();
		this.name = nom;
		this.email = email;
		this.password = password;
		this.office = bureau;
	}

	@Override
	public String toString() {
		return "Responsable [responsibleId=" + responsibleId + ", nom=" + name + ", email=" + email + ", password="
				+ password + ", office=" + office + "]";
	}

}
