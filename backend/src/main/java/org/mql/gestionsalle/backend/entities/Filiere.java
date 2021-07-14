package org.mql.gestionsalle.backend.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "FILIERES")
@Data
@NoArgsConstructor
public class Filiere {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "filiere_id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "level")
	private String level;

	@OneToMany(mappedBy = "filiere", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Student> students = new ArrayList<>();

	public Filiere(String nom, String level) {
		super();
		this.name = nom;
		this.level = level;
	}

	@Override
	public String toString() {
		return "Filiere [id=" + id + ", nom=" + name + ", niveau=" + level + "]";
	}

	public void addStudents(Student etd) {
		if (!students.contains(etd))
			students.add(etd);
	}

}
