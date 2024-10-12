package uo.ri.cws.application.business.mechanic.crud.commands;

import java.util.List;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.business.mechanic.assembler.MechanicAssembler;
import uo.ri.cws.application.business.util.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;

public class FindAllMechanicsTS implements Command<List<MechanicBLDto>> {

	public FindAllMechanicsTS() {
		super();
	}

	@Override
	public List<MechanicBLDto> execute() throws BusinessException {
		return MechanicAssembler
				.toDtoList(PersistenceFactory.forMechanic().findAll());
	}

}
