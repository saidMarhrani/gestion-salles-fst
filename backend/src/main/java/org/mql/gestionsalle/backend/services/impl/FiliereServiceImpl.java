package org.mql.gestionsalle.backend.services.impl;

import org.mql.gestionsalle.backend.entities.Filiere;
import org.mql.gestionsalle.backend.exceptions.ResourceCouldNotBeNullException;
import org.mql.gestionsalle.backend.exceptions.ResourceNotFoundException;
import org.mql.gestionsalle.backend.repositories.FiliereRepository;
import org.mql.gestionsalle.backend.services.FiliereService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FiliereServiceImpl implements FiliereService {

	private FiliereRepository filiereRepository;

	public FiliereServiceImpl(FiliereRepository filiereRepository) {
		this.filiereRepository = filiereRepository;
	}

	@Override
	public Filiere save(Filiere filiere) {
		if (filiere != null)
			return this.filiereRepository.save(filiere);
		throw new ResourceCouldNotBeNullException("Filiere could not be null !!");
	}

	@Override
	public Filiere getFiliere(Long id) {
		return this.filiereRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Filiere that has " + id + ", does not found !!"));
	}

}
