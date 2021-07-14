package org.mql.gestionsalle.backend.services;

import java.util.List;

import org.mql.gestionsalle.backend.entities.Student;

public interface StudentService {

	List<Student> getAllStudents();

	Student getStudent(String cne);

	Student save(Student student);

	Student update(String cne, Student student);

	void delete(String cne);

	Student findByUsername(String username);

	Student findByUsernameAndPassword(String username, String password);

}
