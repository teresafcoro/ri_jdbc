package uo.ri.cws.application.business.mechanic.crud.commands;

import java.util.Optional;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.business.mechanic.assembler.MechanicAssembler;
import uo.ri.cws.application.business.util.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;

public class FindByIdMechanicTS implements Command<Optional<MechanicBLDto>> {

	private String idMechanic;

	public FindByIdMechanicTS(String idMechanic) {
		checkIdMechanic(idMechanic);
		this.idMechanic = idMechanic;
	}

	@Override
	public Optional<MechanicBLDto> execute() throws BusinessException {
		return MechanicAssembler
				.toBLDto(PersistenceFactory.forMechanic().findById(idMechanic));
	}

	private void checkIdMechanic(String idMechanic) {
		Argument.isNotEmpty(idMechanic,
				"El mecánico a encontrar no puede tener id vacío");
		Argument.isNotNull(idMechanic,
				"El mecánico a encontrar no puede tener id nulo");
	}

}
