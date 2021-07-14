package org.mql.gestionsalle.backend.services;

import org.mql.gestionsalle.backend.entities.Filiere;

public interface FiliereService {

	Filiere save(Filiere filiere);
	
	Filiere getFiliere(Long id);

}
