package uo.ri.cws.application.business.payroll.create.commands;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import math.Round;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.contract.ContractService.ContractBLDto;
import uo.ri.cws.application.business.contract.ContractService.ContractState;
import uo.ri.cws.application.business.contract.assembler.ContractAssembler;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.business.mechanic.assembler.MechanicAssembler;
import uo.ri.cws.application.business.payroll.PayrollService.PayrollBLDto;
import uo.ri.cws.application.business.payroll.assembler.PayrollAssembler;
import uo.ri.cws.application.business.professionalgroup.ProfessionalGroupService.ProfessionalGroupBLDto;
import uo.ri.cws.application.business.professionalgroup.assembler.ProfessionalGroupAssembler;
import uo.ri.cws.application.business.util.Command;
import uo.ri.cws.application.business.workorder.WorkOrderService.WorkOrderBLDto;
import uo.ri.cws.application.business.workorder.assembler.WorkOrderAssembler;
import uo.ri.cws.application.persistence.PersistenceFactory;

public class GeneratePayrollsTS implements Command<Void> {

	private LocalDate date;

	public GeneratePayrollsTS() {
		super();
	}

	public GeneratePayrollsTS(LocalDate present) {
		this.date = present;
	}

	@Override
	public Void execute() throws BusinessException {
		if (!getPayrollsGeneratedThisMonth().isEmpty())
			return null;

		List<ContractBLDto> contracts = getValidContracts();
		for (ContractBLDto contractBLDto : contracts) {
			Optional<MechanicBLDto> mechanic = getMechanic(contractBLDto.dni);
			if (mechanic.isPresent()) {
				PayrollBLDto payroll = generatePayroll(mechanic.get(),
						contractBLDto);
				if (payroll == null)
					continue;

				PersistenceFactory.forPayroll()
						.add(PayrollAssembler.toDALDto(payroll));
			}
		}

		return null;
	}

	/*
	 * Obtiene las nóminas generadas este mes. Si hubiese, no se generará
	 * ninguna nómina nueva.
	 */
	private List<PayrollBLDto> getPayrollsGeneratedThisMonth() {
		return PayrollAssembler.toBLDtos(
				PersistenceFactory.forPayroll().findAllGeneratedThisMonth());
	}

	/*
	 * Obtiene los mecánicos que tienen contrato en vigor o que se extingue este
	 * mes.
	 */
	private List<ContractBLDto> getValidContracts() {
		return ContractAssembler.toBLDtos(
				PersistenceFactory.forContract().findValidContracts());
	}

	private Optional<MechanicBLDto> getMechanic(String id) {
		return MechanicAssembler
				.toBLDto(PersistenceFactory.forMechanic().findById(id));
	}

	private PayrollBLDto generatePayroll(MechanicBLDto mechanicBLDto,
			ContractBLDto contractBLDto) {

		PayrollBLDto payroll = new PayrollBLDto();
		payroll.id = UUID.randomUUID().toString();
		payroll.version = 1L;
		payroll.date = getDate();
		payroll.contractId = contractBLDto.id;

		// earnings
		payroll.monthlyWage = calculateMonthlyWage(
				contractBLDto.annualBaseWage);
		payroll.bonus = getBonus(getDate()) ? payroll.monthlyWage : 0.0;

		Optional<ProfessionalGroupBLDto> optionalPG = getProfessionalGroup(
				contractBLDto.professionalGroupName);
		if (optionalPG.isEmpty())
			return null;
		ProfessionalGroupBLDto pg = optionalPG.get();

		payroll.productivityBonus = getProductivityBonus(mechanicBLDto,
				pg.productivityRate);
		payroll.trienniumPayment = getTrienniumPayment(contractBLDto,
				pg.trieniumSalary);
		double grossWage = payroll.monthlyWage + payroll.bonus
				+ payroll.productivityBonus + payroll.trienniumPayment;

		// deductions
		payroll.incomeTax = getIncomeTax(grossWage,
				contractBLDto.annualBaseWage);
		payroll.nic = getNic(contractBLDto.annualBaseWage);
		double deductions = payroll.incomeTax + payroll.nic;

		payroll.netWage = Round.twoCents(grossWage - deductions);
		return payroll;
	}

	// earnings

	private double calculateMonthlyWage(double annualBaseWage) {
		return Round.twoCents(annualBaseWage / 14);
	}

	private LocalDate getDate() {
		if (date == null)
			return LocalDate.now();
		return date;
	}

	private boolean getBonus(LocalDate date) {
		return date.getMonthValue() == 6 | date.getMonthValue() == 12;
	}

	private Optional<ProfessionalGroupBLDto> getProfessionalGroup(String id) {
		return ProfessionalGroupAssembler.toOptionalBLDto(
				PersistenceFactory.forProfessionalGroup().findById(id));
	}

	private List<WorkOrderBLDto> getWorkOrdersForMechanic(String mechanicId) {
		return WorkOrderAssembler.toBLDtos(
				PersistenceFactory.forWorkOrder().findByMechanic(mechanicId));
	}

	/*
	 * Porcentaje del importe total de las workorders asignadas al mecánico
	 */
	private double getProductivityBonus(MechanicBLDto mechanicBLDto,
			double productivityBonusPercentage) {

		double productivityBonus = 0.0;
		List<WorkOrderBLDto> workOrders = getWorkOrdersForMechanic(
				mechanicBLDto.id);
		for (WorkOrderBLDto workOrderBLDto : workOrders)
			productivityBonus += workOrderBLDto.total;
		productivityBonus *= productivityBonusPercentage / 100;
		return Round.twoCents(productivityBonus);
	}

	/*
	 * Por cada tres años completos acumulados en el mismo contrato en vigor, se
	 * acumulará un trienio. Por cada trienio, el mecánico percibirá una
	 * cantidad que depende de su grupo profesional.
	 */
	private double getTrienniumPayment(ContractBLDto contract,
			double trieniumSalary) {

		int triennium = 0;
		int year = getDate().getYear() - 3;
		while (year > 2000) {
			if (contract.state == ContractState.IN_FORCE & contract.startDate
					.isBefore(LocalDate.of(year, getDate().getMonthValue(),
							getDate().getDayOfMonth())))
				triennium++;
			year -= 3;
		}

		return Round.twoCents(triennium * trieniumSalary);
	}

	// deductions

	/*
	 * Se dividen los ingresos anuales por tramos y a cada uno le corresponde un
	 * porcentaje.
	 */
	private double getRate(double annualBaseWage) {
		double rate = 0.0;
		if (annualBaseWage >= 0 & annualBaseWage < 12450)
			rate = 1 - 19.0 / 100;
		if (annualBaseWage >= 12450 & annualBaseWage < 20200)
			rate = 1 - 24.0 / 100;
		if (annualBaseWage >= 20200 & annualBaseWage < 35200)
			rate = 1 - 30.0 / 100;
		if (annualBaseWage >= 35200 & annualBaseWage < 60000)
			rate = 1 - 37.0 / 100;
		if (annualBaseWage >= 60000 & annualBaseWage < 300000)
			rate = 1 - 45.0 / 100;
		if (annualBaseWage >= 300000)
			rate = 1 - 47.0 / 100;
		return rate;
	}

	/*
	 * Se calcula aplicando el porcentaje correspondiente sobre el total bruto
	 * de la nomina.
	 */
	private double getIncomeTax(double grossWage, double annualBaseWage) {
		return Round.twoCents(grossWage - grossWage * getRate(annualBaseWage));
	}

	/*
	 * 5% del salario base anual prorrateado entre 12 meses.
	 */
	private double getNic(double annualBaseWage) {
		double salarioBaseAnualProrrateado = annualBaseWage / 12;
		return Round.twoCents(salarioBaseAnualProrrateado
				- salarioBaseAnualProrrateado * (1 - 5.0 / 100));
	}

}
