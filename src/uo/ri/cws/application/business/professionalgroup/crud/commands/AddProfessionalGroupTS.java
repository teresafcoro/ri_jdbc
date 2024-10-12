package uo.ri.cws.application.business.professionalgroup.crud.commands;

import java.util.UUID;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.professionalgroup.ProfessionalGroupService.ProfessionalGroupBLDto;
import uo.ri.cws.application.business.professionalgroup.assembler.ProfessionalGroupAssembler;
import uo.ri.cws.application.business.util.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;

public class AddProfessionalGroupTS implements Command<ProfessionalGroupBLDto> {

	private ProfessionalGroupBLDto dto;

	public AddProfessionalGroupTS(ProfessionalGroupBLDto dto) {
		checkProfessionalGroup(dto);
		this.dto = dto;
	}

	@Override
	public ProfessionalGroupBLDto execute() throws BusinessException {
		if (PersistenceFactory.forProfessionalGroup().findByName(dto.name)
				.isPresent())
			throw new BusinessException("El grupo profesional a añadir"
					+ " ya está en la base de datos");

		dto.id = UUID.randomUUID().toString();
		dto.version = 1L;

		PersistenceFactory.forProfessionalGroup()
				.add(ProfessionalGroupAssembler.toDALDto(dto));

		return dto;
	}

	private void checkProfessionalGroup(ProfessionalGroupBLDto dto) {
		Argument.isNotNull(dto,
				"El grupo profesional a añadir no puede ser nulo");
		Argument.isNotEmpty(dto.name,
				"El grupo profesional a añadir no puede tener nombre vacío");
		Argument.isNotNull(dto.name,
				"El grupo profesional a añadir no puede tener nombre nulo");
		Argument.isNotNull(dto.trieniumSalary,
				"El grupo profesional a añadir no puede tener importe de los"
						+ " trienios nulo");
		Argument.isTrue(dto.trieniumSalary >= 0,
				"El grupo profesional a añadir no puede tener importe de los"
						+ " trienios negativo");
		Argument.isNotNull(dto.productivityRate,
				"El grupo profesional a añadir no puede tener porcentaje del"
						+ " plus de productividad nulo");
		Argument.isTrue(dto.productivityRate >= 0,
				"El grupo profesional a añadir no puede tener porcentaje del"
						+ " plus de productividad negativo");
	}

}
