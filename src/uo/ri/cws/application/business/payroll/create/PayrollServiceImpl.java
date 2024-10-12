package uo.ri.cws.application.business.payroll.create;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.payroll.PayrollService;
import uo.ri.cws.application.business.payroll.create.commands.DeleteLastPayrollForTS;
import uo.ri.cws.application.business.payroll.create.commands.DeleteLastPayrollsTS;
import uo.ri.cws.application.business.payroll.create.commands.GeneratePayrollsTS;
import uo.ri.cws.application.business.payroll.create.commands.GetAllPayrollsForMechanicTS;
import uo.ri.cws.application.business.payroll.create.commands.GetAllPayrollsForProfessionalGroupTS;
import uo.ri.cws.application.business.payroll.create.commands.GetAllPayrollsTS;
import uo.ri.cws.application.business.payroll.create.commands.GetPayrollDetailsTS;
import uo.ri.cws.application.business.util.CommandExecutor;

public class PayrollServiceImpl implements PayrollService {

	@Override
	public void generatePayrolls() throws BusinessException {
		new CommandExecutor().execute(new GeneratePayrollsTS());
	}

	@Override
	public void generatePayrolls(LocalDate present) throws BusinessException {
		new CommandExecutor().execute(new GeneratePayrollsTS(present));
	}

	@Override
	public void deleteLastPayrollFor(String mechanicDni)
			throws BusinessException {
		new CommandExecutor().execute(new DeleteLastPayrollForTS(mechanicDni));
	}

	@Override
	public void deleteLastPayrolls() throws BusinessException {
		new CommandExecutor().execute(new DeleteLastPayrollsTS());
	}

	@Override
	public Optional<PayrollBLDto> getPayrollDetails(String id)
			throws BusinessException {
		return new CommandExecutor().execute(new GetPayrollDetailsTS(id));
	}

	@Override
	public List<PayrollSummaryBLDto> getAllPayrolls() throws BusinessException {
		return new CommandExecutor().execute(new GetAllPayrollsTS());
	}

	@Override
	public List<PayrollSummaryBLDto> getAllPayrollsForMechanic(String id)
			throws BusinessException {
		return new CommandExecutor()
				.execute(new GetAllPayrollsForMechanicTS(id));
	}

	@Override
	public List<PayrollSummaryBLDto> getAllPayrollsForProfessionalGroup(
			String name) throws BusinessException {
		return new CommandExecutor()
				.execute(new GetAllPayrollsForProfessionalGroupTS(name));
	}

}
