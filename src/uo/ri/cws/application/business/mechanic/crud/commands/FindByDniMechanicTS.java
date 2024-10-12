package uo.ri.cws.application.business.mechanic.crud.commands;

import java.util.Optional;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.business.mechanic.assembler.MechanicAssembler;
import uo.ri.cws.application.business.util.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;

public class FindByDniMechanicTS implements Command<Optional<MechanicBLDto>> {

	private String dniMechanic;

	public FindByDniMechanicTS(String dniMechanic) {
		checkDniMechanic(dniMechanic);
		this.dniMechanic = dniMechanic;
	}

	@Override
	public Optional<MechanicBLDto> execute() throws BusinessException {
		return MechanicAssembler.toBLDto(
				PersistenceFactory.forMechanic().findByDni(dniMechanic));
	}

	private void checkDniMechanic(String dniMechanic) {
		Argument.isNotEmpty(dniMechanic,
				"El mecánico a encontrar no puede tener dni vacío");
		Argument.isNotNull(dniMechanic,
				"El mecánico a encontrar no puede tener dni nulo");
	}

}
