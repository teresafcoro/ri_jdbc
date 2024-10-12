package uo.ri.cws.application.business.payroll.create.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.contract.ContractService.ContractBLDto;
import uo.ri.cws.application.business.contract.assembler.ContractAssembler;
import uo.ri.cws.application.business.payroll.PayrollService.PayrollBLDto;
import uo.ri.cws.application.business.payroll.PayrollService.PayrollSummaryBLDto;
import uo.ri.cws.application.business.payroll.assembler.PayrollAssembler;
import uo.ri.cws.application.business.util.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;

public class GetAllPayrollsForMechanicTS
		implements
			Command<List<PayrollSummaryBLDto>> {

	private String id;

	public GetAllPayrollsForMechanicTS(String id) {
		checkId(id);
		this.id = id;
	}

	@Override
	public List<PayrollSummaryBLDto> execute() throws BusinessException {
		List<PayrollSummaryBLDto> payrolls = new ArrayList<>();

		// if (PersistenceFactory.forMechanic().findById(id).isEmpty())
		if (PersistenceFactory.forMechanic().findByDni(id).isEmpty())
			throw new BusinessException(
					"El mecánico no se encuentra en la base de datos");

		List<ContractBLDto> contracts = getContractsByMechanic();
		for (ContractBLDto contract : contracts) {
			Optional<PayrollBLDto> payroll = getPayrollByContract(contract.id);
			if (payroll.isPresent())
				payrolls.add(PayrollAssembler.toSummaryBLDto(payroll.get()));
		}

		return payrolls;
	}

	private void checkId(String id) {
		Argument.isNotEmpty(id, "El mecánico no puede tener id vacío");
		Argument.isNotNull(id, "El mecánico no puede tener id nulo");
	}

	private List<ContractBLDto> getContractsByMechanic() {
		return ContractAssembler
				.toBLDtos(PersistenceFactory.forContract().findByMechanic(id));
	}

	private Optional<PayrollBLDto> getPayrollByContract(String id) {
		return PayrollAssembler
				.toBLDto(PersistenceFactory.forPayroll().findByContract(id));
	}

}
