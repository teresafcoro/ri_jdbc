package uo.ri.cws.application.business.mechanic.crud.commands;

import java.util.UUID;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.business.mechanic.assembler.MechanicAssembler;
import uo.ri.cws.application.business.util.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;

public class AddMechanicTS implements Command<MechanicBLDto> {

	private MechanicBLDto mechanicBLDto;

	public AddMechanicTS(MechanicBLDto mechanicBLDto) {
		checkMechanic(mechanicBLDto);
		this.mechanicBLDto = mechanicBLDto;
	}

	@Override
	public MechanicBLDto execute() throws BusinessException {
		if (PersistenceFactory.forMechanic().findByDni(mechanicBLDto.dni)
				.isPresent())
			throw new BusinessException(
					"El mec�nico a a�adir ya est� en la base de datos");

		mechanicBLDto.id = UUID.randomUUID().toString();
		mechanicBLDto.version = 1L;

		PersistenceFactory.forMechanic()
				.add(MechanicAssembler.toDALDto(mechanicBLDto));

		return mechanicBLDto;
	}

	private void checkMechanic(MechanicBLDto mechanicBLDto) {
		Argument.isNotNull(mechanicBLDto,
				"El mec�nico a a�adir no puede ser nulo");
		Argument.isNotEmpty(mechanicBLDto.dni,
				"El mec�nico a a�adir no puede tener dni vac�o");
		Argument.isNotNull(mechanicBLDto.dni,
				"El mec�nico a a�adir no puede tener dni nulo");
		Argument.isNotEmpty(mechanicBLDto.name,
				"El mec�nico a a�adir no puede tener nombre vac�o");
		Argument.isNotNull(mechanicBLDto.name,
				"El mec�nico a a�adir no puede tener nombre nulo");
		Argument.isNotEmpty(mechanicBLDto.surname,
				"El mec�nico a a�adir no puede tener apellido vac�o");
		Argument.isNotNull(mechanicBLDto.surname,
				"El mec�nico a a�adir no puede tener apellido nulo");
	}

}
