package uo.ri.cws.application.business.professionalgroup.crud.commands;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.professionalgroup.ProfessionalGroupService.ProfessionalGroupBLDto;
import uo.ri.cws.application.business.professionalgroup.assembler.ProfessionalGroupAssembler;
import uo.ri.cws.application.business.util.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;

public class UpdateProfessionalGroupTS implements Command<Void> {

	private ProfessionalGroupBLDto dto;

	public UpdateProfessionalGroupTS(ProfessionalGroupBLDto dto) {
		checkDto(dto);
		this.dto = dto;
	}

	@Override
	public Void execute() throws BusinessException {
		if (PersistenceFactory.forProfessionalGroup().findByName(dto.name)
				.isEmpty())
			throw new BusinessException("El grupo profesional a actualizar"
					+ " no está en la base de datos");

		PersistenceFactory.forProfessionalGroup()
				.update(ProfessionalGroupAssembler.toDALDto(dto));

		return null;
	}

	private void checkDto(ProfessionalGroupBLDto dto) {
		Argument.isNotNull(dto, "El grupo profesional que intentas actualizar"
				+ " no puede ser nulo");
		Argument.isNotEmpty(dto.name, "El grupo profesional que intentas"
				+ " actualizar no puede tener nombre vacío");
		Argument.isNotNull(dto.name, "El grupo profesional que intentas"
				+ " actualizar no puede tener nombre nulo");
		Argument.isNotNull(dto.trieniumSalary, "El grupo profesional que"
				+ " intentas actualizar no puede tener importe de los trienios"
				+ " nulo");
		Argument.isTrue(dto.trieniumSalary >= 0, "El grupo profesional que"
				+ " intentas actualizar no puede tener importe de los trienios"
				+ " negativo");
		Argument.isNotNull(dto.productivityRate, "El grupo profesional que"
				+ " intentas actualizar no puede tener porcentaje del plus de"
				+ " productividad nulo");
		Argument.isTrue(dto.productivityRate >= 0, "El grupo profesional que"
				+ " intentas actualizar no puede tener porcentaje del plus de"
				+ " productividad negativo");
	}

}
