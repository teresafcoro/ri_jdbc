package uo.ri.cws.application.business.mechanic.crud.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.contract.ContractService.ContractBLDto;
import uo.ri.cws.application.business.contract.assembler.ContractAssembler;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.business.mechanic.assembler.MechanicAssembler;
import uo.ri.cws.application.business.professionalgroup.ProfessionalGroupService.ProfessionalGroupBLDto;
import uo.ri.cws.application.business.professionalgroup.assembler.ProfessionalGroupAssembler;
import uo.ri.cws.application.business.util.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;

public class FindMechanicsInForceInProfessionalGroups
		implements
			Command<List<MechanicBLDto>> {

	private String name;

	public FindMechanicsInForceInProfessionalGroups(String name) {
		checkId(name);
		this.name = name;
	}

	@Override
	public List<MechanicBLDto> execute() throws BusinessException {
		List<MechanicBLDto> mechanics = new ArrayList<MechanicBLDto>();

		Optional<ProfessionalGroupBLDto> professionalGroup = getProfessionalGroup();
		if (professionalGroup.isPresent()) {
			List<ContractBLDto> contracts = getContractsInForceInProfessionalGroup(
					professionalGroup.get().id);
			for (ContractBLDto contractBLDto : contracts) {
				Optional<MechanicBLDto> mechanic = getMechanics(
						contractBLDto.dni);
				if (mechanic.isPresent())
					mechanics.add(mechanic.get());
			}
		}

		return mechanics;
	}

	private Optional<ProfessionalGroupBLDto> getProfessionalGroup() {
		return ProfessionalGroupAssembler.toOptionalBLDto(
				PersistenceFactory.forProfessionalGroup().findByName(name));
	}

	private List<ContractBLDto> getContractsInForceInProfessionalGroup(
			String id) {
		return ContractAssembler.toBLDtos(PersistenceFactory.forContract()
				.findInForceByProfessionalGroupId(id));
	}

	private Optional<MechanicBLDto> getMechanics(String dni) {
		return MechanicAssembler
				.toBLDto(PersistenceFactory.forMechanic().findById(dni));
	}

	private void checkId(String name) {
		Argument.isNotEmpty(name,
				"El grupo profesional no puede tener nombre vacío");
		Argument.isNotNull(name,
				"El grupo profesional no puede tener nombre nulo");
	}

}
