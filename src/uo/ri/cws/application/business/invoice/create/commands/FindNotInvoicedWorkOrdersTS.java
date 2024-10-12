package uo.ri.cws.application.business.invoice.create.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.client.ClientService.ClientBLDto;
import uo.ri.cws.application.business.client.assembler.ClientAssembler;
import uo.ri.cws.application.business.invoice.InvoicingService.WorkOrderForInvoicingBLDto;
import uo.ri.cws.application.business.invoice.assembler.InvoicingAssembler;
import uo.ri.cws.application.business.util.Command;
import uo.ri.cws.application.business.vehicle.VehicleService.VehicleBLDto;
import uo.ri.cws.application.business.vehicle.assembler.VehicleAssembler;
import uo.ri.cws.application.business.workorder.assembler.WorkOrderAssembler;
import uo.ri.cws.application.persistence.PersistenceFactory;

public class FindNotInvoicedWorkOrdersTS
		implements
			Command<List<WorkOrderForInvoicingBLDto>> {

	private String dni;

	public FindNotInvoicedWorkOrdersTS(String dni) {
		checkDniClient(dni);
		this.dni = dni;
	}

	@Override
	public List<WorkOrderForInvoicingBLDto> execute() throws BusinessException {
		// 1. Obtener el id del cliente a partir de su dni
		String clientId = getClient().id;

		// 2. Obtener los ids de los vehiculos de dicho cliente
		List<String> vehiclesIds = getVehiclesIds(clientId);
		if (vehiclesIds.isEmpty())
			return new ArrayList<WorkOrderForInvoicingBLDto>();

		// 3. Encontrar las workorders no facturadas de dichos vehiculos
		return InvoicingAssembler.toInvoicingWorkOrderList(
				WorkOrderAssembler.toBLDtos(PersistenceFactory.forWorkOrder()
						.findNotInvoicedForVehicles(vehiclesIds)));
	}

	private void checkDniClient(String dniClient) {
		Argument.isNotEmpty(dniClient,
				"El dni del cliente no puede estar vacío");
		Argument.isNotNull(dniClient, "El dni del cliente no puede ser nulo");
	}

	private ClientBLDto getClient() throws BusinessException {
		Optional<ClientBLDto> client = ClientAssembler
				.toOptionalBLDto(PersistenceFactory.forClient().findByDni(dni));

		if (client.isEmpty())
			throw new BusinessException("El cliente no fue encontrado en"
					+ " la base de datos a partir del dni introducido");

		return client.get();
	}

	private List<String> getVehiclesIds(String clientId) {
		List<String> vehicleIds = new ArrayList<String>();

		List<VehicleBLDto> vehicles = VehicleAssembler.toBLDtoList(
				PersistenceFactory.forVehicle().findByClient(clientId));

		for (VehicleBLDto vehicle : vehicles)
			vehicleIds.add(vehicle.id);

		return vehicleIds;
	}

}
