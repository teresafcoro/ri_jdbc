package uo.ri.cws.application.business.payroll.create.commands;

import java.util.List;
import java.util.Optional;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.contract.ContractService.ContractBLDto;
import uo.ri.cws.application.business.contract.assembler.ContractAssembler;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.business.mechanic.assembler.MechanicAssembler;
import uo.ri.cws.application.business.payroll.PayrollService.PayrollBLDto;
import uo.ri.cws.application.business.payroll.assembler.PayrollAssembler;
import uo.ri.cws.application.business.util.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;

public class DeleteLastPayrollForTS implements Command<Void> {

	private String mechanicDni;

	public DeleteLastPayrollForTS(String mechanicDni) {
		checkMechanicDni(mechanicDni);
		this.mechanicDni = mechanicDni;
	}

	@Override
	public Void execute() throws BusinessException {
		Optional<MechanicBLDto> mechanic = getMechanic();
		if (mechanic.isEmpty())
			throw new BusinessException(
					"El mecánico no existe en la base de datos");

		List<ContractBLDto> contracts = getMechanicContracts(mechanic.get().id);
		for (ContractBLDto contractBLDto : contracts) {
			List<PayrollBLDto> payrolls = getPayrollsGeneratedThisMonthFor(
					contractBLDto.id);
			for (PayrollBLDto payroll : payrolls)
				PersistenceFactory.forPayroll().remove(payroll.id);
		}

		return null;
	}

	private Optional<MechanicBLDto> getMechanic() {
		return MechanicAssembler.toBLDto(
				PersistenceFactory.forMechanic().findByDni(mechanicDni));
	}

	private List<ContractBLDto> getMechanicContracts(String id) {
		return ContractAssembler
				.toBLDtos(PersistenceFactory.forContract().findByMechanic(id));
	}

	private List<PayrollBLDto> getPayrollsGeneratedThisMonthFor(String id) {
		return PayrollAssembler.toBLDtos(PersistenceFactory.forPayroll()
				.findAllGeneratedThisMonthFor(id));
	}

	private void checkMechanicDni(String dni) {
		Argument.isNotEmpty(dni, "El mecánico no puede tener dni vacío");
		Argument.isNotNull(dni, "El mecánico no puede tener dni nulo");
	}

}
