package org.mql.gestionsalle.backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "rooms")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {

	@Id
	@Column(name = "name")
	private String name;

//	@Enumerated(EnumType.STRING)
	@Column(name = "department")
	private String department;

	@Column(name = "has_projector")
	private Boolean hasProjector;

	@Column(name = "is_reserved")
	private Boolean isReserved;

	@Override
	public String toString() {
		return "Salle [nom=" + name + ", departement=" + department + ", hasProjector=" + hasProjector + "]";
	}

}
