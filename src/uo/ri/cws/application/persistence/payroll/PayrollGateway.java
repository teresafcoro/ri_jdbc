package uo.ri.cws.application.persistence.payroll;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.persistence.Gateway;
import uo.ri.cws.application.persistence.payroll.PayrollGateway.PayrollDALDto;

public interface PayrollGateway extends Gateway<PayrollDALDto> {

	/*
	 * Finds a payroll by the id of a contract
	 * 
	 * @param id, contracts id
	 * 
	 * @return payroll, or null if it does not exists
	 */
	Optional<PayrollDALDto> findByContract(String id);

	/*
	 * Finds all generated payrolls this month
	 * 
	 * @return payrolls, list of payrolls
	 */
	List<PayrollDALDto> findAllGeneratedThisMonth();

	/*
	 * Finds all generated payrolls this month for a contract
	 * 
	 * @param id, contracts id
	 * 
	 * @return payrolls, list of payrolls
	 */
	List<PayrollDALDto> findAllGeneratedThisMonthFor(String contractId);

	public class PayrollDALDto {

		public String id;
		public long version;

		public String contractId;
		public LocalDate date;

		// Earnings
		public double monthlyWage;
		public double bonus;
		public double productivityBonus;
		public double trienniumPayment;

		// Deductions
		public double incomeTax;
		public double nic;
	}

}
