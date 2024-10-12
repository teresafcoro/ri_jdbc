package uo.ri.cws.application.business.mechanic.crud.commands;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.util.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;

public class DeleteMechanicTS implements Command<Void> {

	private String idMechanic;

	public DeleteMechanicTS(String idMechanic) {
		checkIdMechanic(idMechanic);
		this.idMechanic = idMechanic;
	}

	@Override
	public Void execute() throws BusinessException {
		if (PersistenceFactory.forMechanic().findById(idMechanic).isEmpty())
			throw new BusinessException(
					"El mecánico a eliminar no está en la base de datos");

		checkMechanic();

		PersistenceFactory.forMechanic().remove(idMechanic);
		return null;
	}

	private void checkIdMechanic(String idMechanic) {
		Argument.isNotEmpty(idMechanic,
				"El mecánico a eliminar no puede tener id vacío");
		Argument.isNotNull(idMechanic,
				"El mecánico a eliminar no puede tener id nulo");
	}

	/*
	 * Sólo se podrá borrar un mecánico si no tiene órdenes de trabajo (en
	 * cualquier estado), intervenciones, ni contratos.
	 */
	private void checkMechanic() throws BusinessException {
		if (!PersistenceFactory.forWorkOrder().findByMechanic(idMechanic)
				.isEmpty())
			throw new BusinessException(
					"El mecánico a eliminar posee órdenes de trabajo");
		if (!PersistenceFactory.forIntervention().findByMechanic(idMechanic)
				.isEmpty())
			throw new BusinessException(
					"El mecánico a eliminar posee intervenciones");
		if (!PersistenceFactory.forContract().findByMechanic(idMechanic)
				.isEmpty())
			throw new BusinessException(
					"El mecánico a eliminar posee contratos");
	}

}
