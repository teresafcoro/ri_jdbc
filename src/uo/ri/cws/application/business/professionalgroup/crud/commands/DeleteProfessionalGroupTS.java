package uo.ri.cws.application.business.professionalgroup.crud.commands;

import java.util.Optional;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.professionalgroup.ProfessionalGroupService.ProfessionalGroupBLDto;
import uo.ri.cws.application.business.professionalgroup.assembler.ProfessionalGroupAssembler;
import uo.ri.cws.application.business.util.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;

public class DeleteProfessionalGroupTS implements Command<Void> {

	private String name;

	public DeleteProfessionalGroupTS(String name) {
		checkName(name);
		this.name = name;
	}

	@Override
	public Void execute() throws BusinessException {
		Optional<ProfessionalGroupBLDto> op_pg = ProfessionalGroupAssembler
				.toOptionalBLDto(PersistenceFactory.forProfessionalGroup()
						.findByName(name));
		if (op_pg.isEmpty())
			throw new BusinessException("El grupo profesional que intentas"
					+ " eliminar no está en la base de datos");

		ProfessionalGroupBLDto pg = op_pg.get();
		checkProfessionalGroup(pg);

		PersistenceFactory.forProfessionalGroup().remove(pg.id);

		return null;
	}

	private void checkName(String name) {
		Argument.isNotEmpty(name, "El grupo profesional que intentas eliminar"
				+ " no puede tener nombre vacío");
		Argument.isNotNull(name, "El grupo profesional que intentas eliminar"
				+ " no puede tener nombre nulo");
	}

	/*
	 * No se podra borrar un grupo profesional que tenga contratos asociados,
	 * esten o no en vigor.
	 */
	private void checkProfessionalGroup(ProfessionalGroupBLDto pg)
			throws BusinessException {
		if (!PersistenceFactory.forContract().findByProfessionalGroupId(pg.id)
				.isEmpty())
			throw new BusinessException("El grupo profesional que intentas"
					+ " eliminar posee contratos asociados");
	}

}
