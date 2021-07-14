package org.mql.gestionsalle.backend.entities;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
public class Student {

	@Id
	@Column(name = "cne")
	private String cne;

	@Column(nullable = false, name = "lastname")
	private String lastName;

	@Column(nullable = false, name = "firstname")
	private String firstName;

	@Column(nullable = false, name = "username", unique = true)
	private String username;

	@Column(unique = true, nullable = false, name = "phone")
	private String phone;

	@Column(unique = true, nullable = false, name = "email")
	private String email;

	@Column(nullable = false, name = "password")
	@JsonIgnore
	private String password;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "filiere_id")
	private Filiere filiere;

	@JsonIgnore
	@OneToMany(mappedBy = "student")
	private Collection<Reservation> resevations;

	public Student(String cne, String lastName, String firstName, String username, String phone, String email, String password,
			Filiere filiere) {
		super();
		this.cne = cne;
		this.lastName = lastName;
		this.firstName = firstName;
		this.username = username;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.filiere = filiere;
	}

	@Override
	public String toString() {
		return "Student [cne=" + cne + ", lastName=" + lastName + ", firstName=" + firstName + ", phone=" + phone
				+ ", email=" + email + ", password=" + password + "]";
	}

}
