package uo.ri.cws.application.business.invoice.create.commands;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import assertion.Argument;
import math.Round;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.invoice.InvoicingService.InvoiceBLDto;
import uo.ri.cws.application.business.invoice.InvoicingService.InvoiceBLDto.InvoiceState;
import uo.ri.cws.application.business.invoice.assembler.InvoicingAssembler;
import uo.ri.cws.application.business.util.Command;
import uo.ri.cws.application.business.workorder.WorkOrderService.WorkOrderBLDto;
import uo.ri.cws.application.business.workorder.assembler.WorkOrderAssembler;
import uo.ri.cws.application.persistence.PersistenceFactory;

public class CreateInvoiceTS implements Command<InvoiceBLDto> {

	private List<String> workOrderIds;

	public CreateInvoiceTS(List<String> workOrderIds) {
		checkWorkOrdersIds(workOrderIds);
		this.workOrderIds = workOrderIds;
	}

	@Override
	public InvoiceBLDto execute() throws BusinessException {
		// Comprobaciones a las workorders: han de existir y estar acabadas
		List<WorkOrderBLDto> workorders = WorkOrderAssembler.toBLDtos(
				PersistenceFactory.forWorkOrder().findByIds(workOrderIds));
		checkWorkOrdersExist(workorders);
		checkWorkOrdersFinished(workorders);

		long numberInvoice = generateInvoiceNumber();
		LocalDate dateInvoice = LocalDate.now();
		double amount = calculateTotalInvoice(workorders); // vat not included
		double vat = vatPercentage(amount, dateInvoice);
		double total = amount * (1 + vat / 100); // vat included
		total = Round.twoCents(total);

		InvoiceBLDto invoice = createInvoice(numberInvoice, dateInvoice, vat,
				total);
		updateWorkOrder(invoice.id, workorders);

		return invoice;
	}

	private void checkWorkOrdersIds(List<String> workOrderIds) {
		Argument.isTrue(workOrderIds != null,
				"Se requieren ids de workorders para generar la factura");
		Argument.isTrue(!workOrderIds.isEmpty(),
				"Se requieren ids de workorders para generar la factura");

		for (String workOrderId : workOrderIds)
			Argument.isNotEmpty(workOrderId, "Se requieren ids correctos de"
					+ " workorders para generar la factura");
	}

	/*
	 * checks whether every work order exist
	 */
	private void checkWorkOrdersExist(List<WorkOrderBLDto> workorders)
			throws BusinessException {
		if (workorders.isEmpty())
			throw new BusinessException(
					"Los ids no corresponden con workorders de la base de datos");
	}

	/*
	 * checks whether every work order id is FINISHED
	 */
	private void checkWorkOrdersFinished(List<WorkOrderBLDto> workorders)
			throws BusinessException {
		for (WorkOrderBLDto workOrder : workorders) {
			if (!"FINISHED".equalsIgnoreCase(workOrder.state))
				throw new BusinessException(
						"La workorder no se encuentra en estado finished");
		}
	}

	/*
	 * Generates next invoice number (not to be confused with the inner id)
	 */
	private Long generateInvoiceNumber() {
		return PersistenceFactory.forInvoice().getNextInvoiceNumber();
	}

	/*
	 * Compute total amount of the invoice (as the total of individual work
	 * orders' amount)
	 */
	private double calculateTotalInvoice(List<WorkOrderBLDto> workorders) {
		double totalInvoice = 0.0;
		for (WorkOrderBLDto workOrder : workorders)
			totalInvoice += workOrder.total;
		return totalInvoice;
	}

	/*
	 * returns vat percentage
	 */
	private double vatPercentage(double totalInvoice, LocalDate dateInvoice) {
		return LocalDate.parse("2012-07-01").isBefore(dateInvoice)
				? 21.0
				: 18.0;
	}

	/*
	 * Creates the invoice in the database
	 */
	private InvoiceBLDto createInvoice(long numberInvoice,
			LocalDate dateInvoice, double vat, double total) {
		InvoiceBLDto invoice = new InvoiceBLDto();
		invoice.id = UUID.randomUUID().toString();
		invoice.number = numberInvoice;
		invoice.date = dateInvoice;
		invoice.vat = vat;
		invoice.total = total;
		invoice.state = InvoiceState.NOT_YET_PAID;
		invoice.version = 1L;

		PersistenceFactory.forInvoice()
				.add(InvoicingAssembler.toDALDto(invoice));

		return invoice;
	}

	/*
	 * Set the invoice number field in work order table to the invoice number
	 * generated. Also sets state to INVOICED for every workorder and updates
	 * the workorders version
	 */
	private void updateWorkOrder(String invoiceId,
			List<WorkOrderBLDto> workOrders) {
		for (WorkOrderBLDto workorder : workOrders) {
			workorder.invoiceId = invoiceId;
			PersistenceFactory.forWorkOrder()
					.update(WorkOrderAssembler.toDALDto(workorder));
		}
	}

}
