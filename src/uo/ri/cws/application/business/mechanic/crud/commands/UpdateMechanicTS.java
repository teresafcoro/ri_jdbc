package uo.ri.cws.application.business.mechanic.crud.commands;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.business.mechanic.assembler.MechanicAssembler;
import uo.ri.cws.application.business.util.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;

public class UpdateMechanicTS implements Command<Void> {

	private MechanicBLDto mechanicBLDto;

	public UpdateMechanicTS(MechanicBLDto mechanicBLDto) {
		checkMechanic(mechanicBLDto);
		this.mechanicBLDto = mechanicBLDto;
	}

	@Override
	public Void execute() throws BusinessException {
		if (PersistenceFactory.forMechanic().findByDni(mechanicBLDto.dni)
				.isEmpty())
			throw new BusinessException("El mec�nico a actualizar no est� en"
					+ " la base de datos");

		PersistenceFactory.forMechanic()
				.update(MechanicAssembler.toDALDto(mechanicBLDto));

		return null;
	}

	private void checkMechanic(MechanicBLDto mechanicBLDto) {
		Argument.isNotEmpty(mechanicBLDto.dni,
				"El mec�nico a actualizar no puede tener dni vac�o");
		Argument.isNotNull(mechanicBLDto.dni,
				"El mec�nico a actualizar no puede tener dni nulo");
		Argument.isNotEmpty(mechanicBLDto.name,
				"El mec�nico a actualizar no puede tener nombre vac�o");
		Argument.isNotNull(mechanicBLDto.name,
				"El mec�nico a actualizar no puede tener nombre nulo");
		Argument.isNotEmpty(mechanicBLDto.surname,
				"El mec�nico a actualizar no puede tener apellido vac�o");
		Argument.isNotNull(mechanicBLDto.surname,
				"El mec�nico a actualizar no puede tener apellido nulo");
	}

}
