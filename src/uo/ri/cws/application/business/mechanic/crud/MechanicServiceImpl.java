package uo.ri.cws.application.business.mechanic.crud;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicService;
import uo.ri.cws.application.business.mechanic.crud.commands.AddMechanicTS;
import uo.ri.cws.application.business.mechanic.crud.commands.DeleteMechanicTS;
import uo.ri.cws.application.business.mechanic.crud.commands.FindAllMechanicsTS;
import uo.ri.cws.application.business.mechanic.crud.commands.FindByDniMechanicTS;
import uo.ri.cws.application.business.mechanic.crud.commands.FindByIdMechanicTS;
import uo.ri.cws.application.business.mechanic.crud.commands.FindMechanicsInForceTS;
import uo.ri.cws.application.business.mechanic.crud.commands.FindMechanicsInProfessionalGroupsTS;
import uo.ri.cws.application.business.mechanic.crud.commands.UpdateMechanicTS;
import uo.ri.cws.application.business.util.CommandExecutor;

public class MechanicServiceImpl implements MechanicService {

	@Override
	public MechanicBLDto addMechanic(MechanicBLDto mechanic)
			throws BusinessException {
		return new CommandExecutor().execute(new AddMechanicTS(mechanic));
	}

	@Override
	public void deleteMechanic(String idMechanic) throws BusinessException {
		new CommandExecutor().execute(new DeleteMechanicTS(idMechanic));
	}

	@Override
	public void updateMechanic(MechanicBLDto mechanic)
			throws BusinessException {
		new CommandExecutor().execute(new UpdateMechanicTS(mechanic));
	}

	@Override
	public Optional<MechanicBLDto> findMechanicById(String idMechanic)
			throws BusinessException {
		return new CommandExecutor()
				.execute(new FindByIdMechanicTS(idMechanic));
	}

	@Override
	public Optional<MechanicBLDto> findMechanicByDni(String dniMechanic)
			throws BusinessException {
		return new CommandExecutor()
				.execute(new FindByDniMechanicTS(dniMechanic));
	}

	@Override
	public List<MechanicBLDto> findAllMechanics() throws BusinessException {
		return new CommandExecutor().execute(new FindAllMechanicsTS());
	}

	@Override
	public List<MechanicBLDto> findMechanicsInForce() throws BusinessException {
		return new CommandExecutor().execute(new FindMechanicsInForceTS());
	}

	@Override
	public List<MechanicBLDto> findMechanicsWithContractInForceInContractType(
			String name) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MechanicBLDto> findMechanicsInProfessionalGroups(String name)
			throws BusinessException {
		return new CommandExecutor()
				.execute(new FindMechanicsInProfessionalGroupsTS(name));
	}

}
