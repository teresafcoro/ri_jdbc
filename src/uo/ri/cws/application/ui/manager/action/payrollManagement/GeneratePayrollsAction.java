package uo.ri.cws.application.ui.manager.action.payrollManagement;

import java.util.List;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.payroll.PayrollService;
import uo.ri.cws.application.business.payroll.PayrollService.PayrollSummaryBLDto;

public class GeneratePayrollsAction implements Action {

	private PayrollService ps = BusinessFactory.forPayrollService();

	@Override
	public void execute() throws BusinessException {
		ps.generatePayrolls();
		List<PayrollSummaryBLDto> all = ps.getAllPayrolls();

		Console.printf("Generated %d new payrolls", all.size());
	}

}
