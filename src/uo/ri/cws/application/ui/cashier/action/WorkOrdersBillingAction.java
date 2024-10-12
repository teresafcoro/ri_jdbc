package uo.ri.cws.application.ui.cashier.action;

import java.util.ArrayList;
import java.util.List;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.invoice.InvoicingService.InvoiceBLDto;
import uo.ri.cws.application.ui.util.Printer;

public class WorkOrdersBillingAction implements Action {

	@Override
	public void execute() throws BusinessException {
		List<String> workOrderIds = new ArrayList<String>();

		// Get info
		// type work order ids to be invoiced in the invoice
		do {
			String id = Console.readString("Type work order ids:  ");
			workOrderIds.add(id);
		} while (nextWorkorder());

		// Process
		InvoiceBLDto invoice = BusinessFactory.forInvoicingService()
				.createInvoiceFor(workOrderIds);

		// Print result
		Console.println("\nInvoice created \n");
		Printer.printInvoice(invoice);
	}

	/*
	 * read work order ids to invoice
	 */
	private boolean nextWorkorder() {
		return Console.readString(" Any other workorder? (y/n) ")
				.equalsIgnoreCase("y");
	}

}
