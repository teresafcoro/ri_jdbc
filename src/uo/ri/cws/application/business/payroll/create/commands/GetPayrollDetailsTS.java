package uo.ri.cws.application.business.payroll.create.commands;

import java.util.Optional;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.payroll.PayrollService.PayrollBLDto;
import uo.ri.cws.application.business.payroll.assembler.PayrollAssembler;
import uo.ri.cws.application.business.util.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;

public class GetPayrollDetailsTS implements Command<Optional<PayrollBLDto>> {

	private String id;

	public GetPayrollDetailsTS(String id) {
		checkId(id);
		this.id = id;
	}

	@Override
	public Optional<PayrollBLDto> execute() throws BusinessException {
		return PayrollAssembler
				.toBLDto(PersistenceFactory.forPayroll().findById(id));
	}

	private void checkId(String id) {
		Argument.isNotEmpty(id,
				"La nómina que intentas encontrar no puede tener id vacío");
		Argument.isNotNull(id,
				"La nómina que intentas encontrar no puede tener id nulo");
	}

}
