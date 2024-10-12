package uo.ri.cws.application.business.payroll.create.commands;

import java.util.List;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.payroll.PayrollService.PayrollSummaryBLDto;
import uo.ri.cws.application.business.payroll.assembler.PayrollAssembler;
import uo.ri.cws.application.business.util.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;

public class GetAllPayrollsTS implements Command<List<PayrollSummaryBLDto>> {

	public GetAllPayrollsTS() {
		super();
	}

	@Override
	public List<PayrollSummaryBLDto> execute() throws BusinessException {
		return PayrollAssembler
				.toSummaryBLDtos(PersistenceFactory.forPayroll().findAll());
	}

}
