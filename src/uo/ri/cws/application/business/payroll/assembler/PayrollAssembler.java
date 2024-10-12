package uo.ri.cws.application.business.payroll.assembler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.payroll.PayrollService.PayrollBLDto;
import uo.ri.cws.application.business.payroll.PayrollService.PayrollSummaryBLDto;
import uo.ri.cws.application.persistence.payroll.PayrollGateway.PayrollDALDto;

public class PayrollAssembler {

	public static PayrollSummaryBLDto toSummaryBLDto(PayrollBLDto arg) {
		PayrollSummaryBLDto result = new PayrollSummaryBLDto();
		result.id = arg.id;
		result.version = arg.version;

		result.date = arg.date;
		result.netWage = arg.monthlyWage;
		return result;
	}

	public static List<PayrollSummaryBLDto> toSummaryBLDtos(
			List<PayrollDALDto> arg) {
		List<PayrollSummaryBLDto> result = new ArrayList<PayrollSummaryBLDto>();
		List<PayrollBLDto> arg2 = toBLDtos(arg);
		for (PayrollBLDto p : arg2)
			result.add(toSummaryBLDto(p));
		return result;
	}

	private static PayrollBLDto toPayrollBLDto(PayrollDALDto arg) {
		PayrollBLDto result = new PayrollBLDto();
		result.id = arg.id;
		result.version = arg.version;

		result.contractId = arg.contractId;
		result.date = arg.date;

		result.monthlyWage = arg.monthlyWage;
		result.bonus = arg.bonus;
		result.productivityBonus = arg.productivityBonus;
		result.trienniumPayment = arg.trienniumPayment;

		result.incomeTax = arg.incomeTax;
		result.nic = arg.nic;

		result.netWage = arg.bonus + arg.monthlyWage + arg.productivityBonus
				+ arg.trienniumPayment - arg.incomeTax - arg.nic;
		return result;
	}

	public static Optional<PayrollBLDto> toBLDto(Optional<PayrollDALDto> arg) {
		Optional<PayrollBLDto> result = arg.isEmpty()
				? Optional.ofNullable(null)
				: Optional.ofNullable(toPayrollBLDto(arg.get()));
		return result;
	}

	public static PayrollDALDto toDALDto(PayrollBLDto arg) {
		PayrollDALDto result = new PayrollDALDto();
		result.id = arg.id;
		result.version = arg.version;

		result.contractId = arg.contractId;
		result.date = arg.date;

		result.monthlyWage = arg.monthlyWage;
		result.bonus = arg.bonus;
		result.productivityBonus = arg.productivityBonus;
		result.trienniumPayment = arg.trienniumPayment;

		result.incomeTax = arg.incomeTax;
		result.nic = arg.nic;
		return result;
	}

	public static List<PayrollBLDto> toBLDtos(List<PayrollDALDto> arg) {
		List<PayrollBLDto> result = new ArrayList<PayrollBLDto>();
		for (PayrollDALDto p : arg)
			result.add(toPayrollBLDto(p));
		return result;
	}

}
