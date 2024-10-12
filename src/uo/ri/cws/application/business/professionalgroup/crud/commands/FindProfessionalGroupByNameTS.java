package uo.ri.cws.application.business.professionalgroup.crud.commands;

import java.util.Optional;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.professionalgroup.ProfessionalGroupService.ProfessionalGroupBLDto;
import uo.ri.cws.application.business.professionalgroup.assembler.ProfessionalGroupAssembler;
import uo.ri.cws.application.business.util.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;

public class FindProfessionalGroupByNameTS
		implements
			Command<Optional<ProfessionalGroupBLDto>> {

	private String name;

	public FindProfessionalGroupByNameTS(String name) {
		checkName(name);
		this.name = name;
	}

	@Override
	public Optional<ProfessionalGroupBLDto> execute() throws BusinessException {
		return ProfessionalGroupAssembler.toOptionalBLDto(
				PersistenceFactory.forProfessionalGroup().findByName(name));
	}

	private void checkName(String name) {
		Argument.isNotEmpty(name, "El grupo profesional que intentas encontrar"
				+ " no puede tener nombre vacío");
		Argument.isNotNull(name, "El grupo profesional que intentas encontrar"
				+ " no puede tener nombre nulo");
	}

}
