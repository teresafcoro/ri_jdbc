package uo.ri.cws.application.ui.manager.action.payrollManagement;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.ui.util.Printer;

public class ListAllPayrollsAction implements Action {

	@Override
	public void execute() throws BusinessException {
		Console.println("\nList of payrolls \n");

		Printer.printPayrollsSummary(
				BusinessFactory.forPayrollService().getAllPayrolls());
	}

}
