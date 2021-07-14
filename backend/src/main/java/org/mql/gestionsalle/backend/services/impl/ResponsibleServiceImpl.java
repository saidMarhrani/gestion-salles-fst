package org.mql.gestionsalle.backend.services.impl;

import lombok.RequiredArgsConstructor;
import org.mql.gestionsalle.backend.entities.Responsible;
import org.mql.gestionsalle.backend.exceptions.ResourceCouldNotBeNullException;
import org.mql.gestionsalle.backend.repositories.ResponsibleRepository;
import org.mql.gestionsalle.backend.services.ResponsibleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ResponsibleServiceImpl implements ResponsibleService {

	private final ResponsibleRepository responsibleRepository;

	@Override
	public Responsible getResponsible() {
		return this.responsibleRepository.findOne();
	}

	@Override
	public Responsible save(Responsible responsible) {
		if (responsible != null)
			return this.responsibleRepository.save(responsible);
		throw new ResourceCouldNotBeNullException("Responsible could not be null !!");
	}

	@Override
	public Responsible update(Long id, Responsible responsible) {
		Responsible oldResponsible = getResponsible();
		responsible.setResponsibleId(oldResponsible.getResponsibleId());
		return this.responsibleRepository.save(responsible);
	}

	@Override
	public void delete(Long id) {
		Responsible oldResponsible = getResponsible();
		this.responsibleRepository.delete(oldResponsible);
	}

}
