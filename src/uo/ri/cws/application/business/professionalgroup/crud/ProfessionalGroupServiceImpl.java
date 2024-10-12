package uo.ri.cws.application.business.professionalgroup.crud;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.professionalgroup.ProfessionalGroupService;
import uo.ri.cws.application.business.professionalgroup.crud.commands.AddProfessionalGroupTS;
import uo.ri.cws.application.business.professionalgroup.crud.commands.DeleteProfessionalGroupTS;
import uo.ri.cws.application.business.professionalgroup.crud.commands.FindAllProfessionalGroupsTS;
import uo.ri.cws.application.business.professionalgroup.crud.commands.FindProfessionalGroupByNameTS;
import uo.ri.cws.application.business.professionalgroup.crud.commands.UpdateProfessionalGroupTS;
import uo.ri.cws.application.business.util.CommandExecutor;

public class ProfessionalGroupServiceImpl implements ProfessionalGroupService {

	@Override
	public ProfessionalGroupBLDto addProfessionalGroup(
			ProfessionalGroupBLDto dto) throws BusinessException {
		return new CommandExecutor().execute(new AddProfessionalGroupTS(dto));
	}

	@Override
	public void deleteProfessionalGroup(String name) throws BusinessException {
		new CommandExecutor().execute(new DeleteProfessionalGroupTS(name));
	}

	@Override
	public void updateProfessionalGroup(ProfessionalGroupBLDto dto)
			throws BusinessException {
		new CommandExecutor().execute(new UpdateProfessionalGroupTS(dto));
	}

	@Override
	public Optional<ProfessionalGroupBLDto> findProfessionalGroupByName(
			String name) throws BusinessException {
		return new CommandExecutor()
				.execute(new FindProfessionalGroupByNameTS(name));
	}

	@Override
	public List<ProfessionalGroupBLDto> findAllProfessionalGroups()
			throws BusinessException {
		return new CommandExecutor().execute(new FindAllProfessionalGroupsTS());
	}

}
