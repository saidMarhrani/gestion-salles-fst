package org.mql.gestionsalle.backend.repositories;

import org.mql.gestionsalle.backend.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    Optional<Student> findByUsername(String username);

    Optional<Student> findByUsernameAndPassword(String username, String password);

}
