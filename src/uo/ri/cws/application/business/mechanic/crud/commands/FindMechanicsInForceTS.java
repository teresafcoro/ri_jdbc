package uo.ri.cws.application.business.mechanic.crud.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.contract.ContractService.ContractBLDto;
import uo.ri.cws.application.business.contract.assembler.ContractAssembler;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.business.mechanic.assembler.MechanicAssembler;
import uo.ri.cws.application.business.util.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;

public class FindMechanicsInForceTS implements Command<List<MechanicBLDto>> {

	public FindMechanicsInForceTS() {
		super();
	}

	@Override
	public List<MechanicBLDto> execute() throws BusinessException {
		List<MechanicBLDto> mechanics = new ArrayList<MechanicBLDto>();

		List<ContractBLDto> contracts = getContractsInForce();
		for (ContractBLDto contractBLDto : contracts) {
			Optional<MechanicBLDto> mechanic = getMechanicById(
					contractBLDto.dni);
			if (mechanic.isPresent())
				mechanics.add(mechanic.get());
		}

		return mechanics;
	}

	private List<ContractBLDto> getContractsInForce() {
		return ContractAssembler
				.toBLDtos(PersistenceFactory.forContract().findInForce());
	}

	private Optional<MechanicBLDto> getMechanicById(String dni) {
		return MechanicAssembler
				.toBLDto(PersistenceFactory.forMechanic().findById(dni));
	}

}
