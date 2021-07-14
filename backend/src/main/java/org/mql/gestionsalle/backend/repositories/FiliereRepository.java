package org.mql.gestionsalle.backend.repositories;

import org.mql.gestionsalle.backend.entities.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FiliereRepository extends JpaRepository<Filiere, Long> {

}
