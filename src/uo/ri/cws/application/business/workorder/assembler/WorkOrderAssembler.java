package uo.ri.cws.application.business.workorder.assembler;

import java.util.ArrayList;
import java.util.List;

import uo.ri.cws.application.business.workorder.WorkOrderService.WorkOrderBLDto;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway.WorkOrderDALDto;

public class WorkOrderAssembler {

	public static WorkOrderBLDto toBLDto(WorkOrderDALDto arg) {
		WorkOrderBLDto result = new WorkOrderBLDto();
		result.id = arg.id;
		result.version = arg.version;

		result.description = arg.description;
		result.date = arg.date;
		result.total = arg.amount;
		result.state = arg.state;
		result.vehicleId = arg.vehicle_id;
		result.mechanicId = arg.mechanic_id;
		result.invoiceId = arg.invoice_id;
		return result;
	}

	public static List<WorkOrderBLDto> toBLDtos(List<WorkOrderDALDto> arg) {
		List<WorkOrderBLDto> result = new ArrayList<WorkOrderBLDto>();
		for (WorkOrderDALDto w : arg)
			result.add(toBLDto(w));
		return result;
	}

	public static WorkOrderDALDto toDALDto(WorkOrderBLDto arg) {
		WorkOrderDALDto result = new WorkOrderDALDto();
		result.id = arg.id;
		result.version = arg.version;

		result.description = arg.description;
		result.date = arg.date;
		result.amount = arg.total;
		result.state = arg.state;
		result.vehicle_id = arg.vehicleId;
		result.mechanic_id = arg.mechanicId;
		result.invoice_id = arg.invoiceId;
		return result;
	}

}
