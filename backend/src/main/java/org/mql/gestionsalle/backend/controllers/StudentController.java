package org.mql.gestionsalle.backend.controllers;

import java.util.List;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.mql.gestionsalle.backend.entities.Filiere;
import org.mql.gestionsalle.backend.entities.Student;
import org.mql.gestionsalle.backend.services.FiliereService;
import org.mql.gestionsalle.backend.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/students")
@RequiredArgsConstructor
public class StudentController {

	private final StudentService etudiantService;
	private final FiliereService filiereService;

	@GetMapping
	public ResponseEntity<List<Student>> getAllStudents() {
		return ResponseEntity.ok().body(this.etudiantService.getAllStudents());
	}

	@GetMapping("/{cne}")
	public ResponseEntity<Student> getStudent(@PathVariable String cne) {

		Student student = this.etudiantService.getStudent(cne);
		return ResponseEntity.ok().body(student);
	}

	@GetMapping("/{username}")
	public ResponseEntity<Student> getStudentByUsername(@PathVariable String username) {

		Student student = this.etudiantService.findByUsername(username);
		return ResponseEntity.ok().body(student);
	}

	@PostMapping
	public ResponseEntity<Student> save(@RequestParam(name = "idFiliere") Long idFiliere,
			@Valid @RequestBody Student etudiant) {

		Filiere filiere = this.filiereService.getFiliere(idFiliere);
		etudiant.setFiliere(filiere);
		this.etudiantService.save(etudiant);

		return ResponseEntity.ok().body(etudiant);
	}

}
