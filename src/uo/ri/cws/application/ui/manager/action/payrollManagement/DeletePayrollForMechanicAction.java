package uo.ri.cws.application.ui.manager.action.payrollManagement;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;

public class DeletePayrollForMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {
		// Get info
		String dni = Console.readString("Mechanic dni ");

		BusinessFactory.forPayrollService().deleteLastPayrollFor(dni);

		// Print result
		Console.println(String.format(
				"Last payroll for mechanic %s successfully deleted", dni));
	}

}
