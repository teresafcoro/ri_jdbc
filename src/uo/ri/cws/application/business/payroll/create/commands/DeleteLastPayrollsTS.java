package uo.ri.cws.application.business.payroll.create.commands;

import java.util.List;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.payroll.PayrollService.PayrollBLDto;
import uo.ri.cws.application.business.payroll.assembler.PayrollAssembler;
import uo.ri.cws.application.business.util.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;

public class DeleteLastPayrollsTS implements Command<Void> {

	public DeleteLastPayrollsTS() {
		super();
	}

	@Override
	public Void execute() throws BusinessException {
		// Nóminas generadas el mes en curso
		List<PayrollBLDto> payrolls = PayrollAssembler.toBLDtos(
				PersistenceFactory.forPayroll().findAllGeneratedThisMonth());
		for (PayrollBLDto payroll : payrolls)
			PersistenceFactory.forPayroll().remove(payroll.id);
		return null;
	}

}
