package uo.ri.cws.application.business.invoice.assembler;

import java.util.ArrayList;
import java.util.List;

import uo.ri.cws.application.business.invoice.InvoicingService.InvoiceBLDto;
import uo.ri.cws.application.business.invoice.InvoicingService.InvoiceBLDto.InvoiceState;
import uo.ri.cws.application.business.invoice.InvoicingService.WorkOrderForInvoicingBLDto;
import uo.ri.cws.application.business.workorder.WorkOrderService.WorkOrderBLDto;
import uo.ri.cws.application.persistence.invoice.InvoiceGateway.InvoiceDALDto;

public class InvoicingAssembler {

	public static InvoiceDALDto toDALDto(InvoiceBLDto arg) {
		InvoiceDALDto result = new InvoiceDALDto();
		result.id = arg.id;
		result.number = arg.number;
		result.state = arg.state.name();
		result.date = arg.date;
		result.amount = arg.total;
		result.vat = arg.vat;
		result.version = arg.version;
		return result;
	}

	public static InvoiceBLDto toDto(InvoiceDALDto arg) {
		InvoiceBLDto result = new InvoiceBLDto();
		result.id = arg.id;
		result.number = arg.number;
		result.state = InvoiceState.valueOf(arg.state);
		result.date = arg.date;
		result.total = arg.amount;
		result.vat = arg.vat;
		result.version = arg.version;
		return result;
	}

	public static List<WorkOrderForInvoicingBLDto> toInvoicingWorkOrderList(
			List<WorkOrderBLDto> arg) {
		List<WorkOrderForInvoicingBLDto> result = new ArrayList<>();
		for (WorkOrderBLDto record : arg)
			result.add(toDto(record));
		return result;
	}

	private static WorkOrderForInvoicingBLDto toDto(WorkOrderBLDto record) {
		WorkOrderForInvoicingBLDto dto = new WorkOrderForInvoicingBLDto();
		dto.id = record.id;
		dto.description = record.description;
		dto.date = record.date;
		dto.state = record.state;
		dto.total = record.total;
		dto.version = record.version;
		return dto;
	}

}
