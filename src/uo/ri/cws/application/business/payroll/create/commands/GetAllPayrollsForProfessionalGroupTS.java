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
import uo.ri.cws.application.business.professionalgroup.ProfessionalGroupService.ProfessionalGroupBLDto;
import uo.ri.cws.application.business.professionalgroup.assembler.ProfessionalGroupAssembler;
import uo.ri.cws.application.business.util.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;

public class GetAllPayrollsForProfessionalGroupTS
		implements
			Command<List<PayrollSummaryBLDto>> {

	private String name;

	public GetAllPayrollsForProfessionalGroupTS(String name) {
		checkName(name);
		this.name = name;
	}

	@Override
	public List<PayrollSummaryBLDto> execute() throws BusinessException {
		List<PayrollSummaryBLDto> payrolls = new ArrayList<>();

		Optional<ProfessionalGroupBLDto> professionalGroup = getProfessionalGroup();
		if (professionalGroup.isEmpty())
			throw new BusinessException(
					"No existe el grupo profesional en la base de datos");

		List<ContractBLDto> contracts = getContractsInProfessionalGroup(
				professionalGroup.get().id);
		for (ContractBLDto contract : contracts) {
			Optional<PayrollBLDto> payroll = getPayrollByContract(contract.id);
			if (payroll.isPresent())
				payrolls.add(PayrollAssembler.toSummaryBLDto(payroll.get()));
		}

		return payrolls;
	}

	private void checkName(String name) {
		Argument.isNotEmpty(name,
				"El grupo profesional no puede tener nombre vacío");
		Argument.isNotNull(name,
				"El grupo profesional no puede tener nombre nulo");
	}

	private Optional<ProfessionalGroupBLDto> getProfessionalGroup() {
		return ProfessionalGroupAssembler.toOptionalBLDto(
				PersistenceFactory.forProfessionalGroup().findByName(name));
	}

	private List<ContractBLDto> getContractsInProfessionalGroup(String id) {
		return ContractAssembler.toBLDtos(
				PersistenceFactory.forContract().findByProfessionalGroupId(id));
	}

	private Optional<PayrollBLDto> getPayrollByContract(String id) {
		return PayrollAssembler
				.toBLDto(PersistenceFactory.forPayroll().findByContract(id));
	}

}
