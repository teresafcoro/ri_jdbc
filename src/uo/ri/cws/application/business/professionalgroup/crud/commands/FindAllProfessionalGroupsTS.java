package uo.ri.cws.application.business.professionalgroup.crud.commands;

import java.util.List;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.professionalgroup.ProfessionalGroupService.ProfessionalGroupBLDto;
import uo.ri.cws.application.business.professionalgroup.assembler.ProfessionalGroupAssembler;
import uo.ri.cws.application.business.util.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;

public class FindAllProfessionalGroupsTS
		implements
			Command<List<ProfessionalGroupBLDto>> {

	public FindAllProfessionalGroupsTS() {
		super();
	}

	@Override
	public List<ProfessionalGroupBLDto> execute() throws BusinessException {
		return ProfessionalGroupAssembler.toBLDtoList(
				PersistenceFactory.forProfessionalGroup().findAll());
	}

}
