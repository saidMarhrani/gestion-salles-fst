package org.mql.gestionsalle.backend.services;

import org.mql.gestionsalle.backend.entities.Responsible;

public interface ResponsibleService {

	Responsible getResponsible();

	Responsible save(Responsible responsable);

	Responsible update(Long id, Responsible responsable);

	void delete(Long id);

}
