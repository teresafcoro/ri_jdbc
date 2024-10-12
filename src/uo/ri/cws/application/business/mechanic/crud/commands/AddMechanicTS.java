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
					"El mecánico a añadir ya está en la base de datos");

		mechanicBLDto.id = UUID.randomUUID().toString();
		mechanicBLDto.version = 1L;

		PersistenceFactory.forMechanic()
				.add(MechanicAssembler.toDALDto(mechanicBLDto));

		return mechanicBLDto;
	}

	private void checkMechanic(MechanicBLDto mechanicBLDto) {
		Argument.isNotNull(mechanicBLDto,
				"El mecánico a añadir no puede ser nulo");
		Argument.isNotEmpty(mechanicBLDto.dni,
				"El mecánico a añadir no puede tener dni vacío");
		Argument.isNotNull(mechanicBLDto.dni,
				"El mecánico a añadir no puede tener dni nulo");
		Argument.isNotEmpty(mechanicBLDto.name,
				"El mecánico a añadir no puede tener nombre vacío");
		Argument.isNotNull(mechanicBLDto.name,
				"El mecánico a añadir no puede tener nombre nulo");
		Argument.isNotEmpty(mechanicBLDto.surname,
				"El mecánico a añadir no puede tener apellido vacío");
		Argument.isNotNull(mechanicBLDto.surname,
				"El mecánico a añadir no puede tener apellido nulo");
	}

}
