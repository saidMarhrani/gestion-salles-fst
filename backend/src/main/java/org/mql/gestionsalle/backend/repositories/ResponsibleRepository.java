package org.mql.gestionsalle.backend.repositories;

import org.mql.gestionsalle.backend.entities.Responsible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ResponsibleRepository extends JpaRepository<Responsible, Long> {

	@Transactional
	@Query("select r from Responsible r where r.responsibleId = 1")
	Responsible findOne();

}
