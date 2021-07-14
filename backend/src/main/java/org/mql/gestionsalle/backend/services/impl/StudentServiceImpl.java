package org.mql.gestionsalle.backend.services.impl;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.mql.gestionsalle.backend.entities.Student;
import org.mql.gestionsalle.backend.exceptions.ResourceCouldNotBeNullException;
import org.mql.gestionsalle.backend.exceptions.ResourceNotFoundException;
import org.mql.gestionsalle.backend.repositories.StudentRepository;
import org.mql.gestionsalle.backend.services.StudentService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

	private final StudentRepository studentRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public List<Student> getAllStudents() {
		return this.studentRepository.findAll();
	}

	@Override
	public Student getStudent(String cne) {
		return this.studentRepository.findById(cne)
				.orElseThrow(() -> new ResourceNotFoundException("Student that has " + cne + ", does not found !!"));
	}

	@Override
	public Student save(Student student) {

		if (student != null){

			student.setPassword(passwordEncoder.encode(student.getPassword()));
			return this.studentRepository.save(student);
		}
		throw new ResourceCouldNotBeNullException("Student could not be null !!");
	}

	@Override
	public Student update(String cne, Student etudiant) {
		Student student = getStudent(cne);
		etudiant.setCne(student.getCne());
		return this.studentRepository.save(etudiant);
	}

	@Override
	public void delete(String cne) {
		Student student = getStudent(cne);
		this.studentRepository.delete(student);
	}

	@Override
	public Student findByUsername(String username) {
		return this.studentRepository.findByUsername(username).get();
	}

	public Student findByUsernameAndPassword(String username, String password){
		return this.studentRepository.findByUsernameAndPassword(username, password).orElseThrow(() -> new ResourceNotFoundException("Student credentials does not matches"));
	}

}
