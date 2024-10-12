package uo.ri.cws.application.persistence.payroll.assembler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.persistence.payroll.PayrollGateway.PayrollDALDto;

public class PayrollAssembler {

	public static Optional<PayrollDALDto> toPayrollDALDto(ResultSet m)
			throws SQLException {
		if (m.next()) {
			return Optional.of(resultSetToPayrollDALDto(m));
		} else
			return Optional.ofNullable(null);
	}

	public static List<PayrollDALDto> toPayrollDALDtoList(ResultSet rs)
			throws SQLException {
		List<PayrollDALDto> res = new ArrayList<>();
		while (rs.next()) {
			res.add(resultSetToPayrollDALDto(rs));
		}

		return res;
	}

	private static PayrollDALDto resultSetToPayrollDALDto(ResultSet rs)
			throws SQLException {
		PayrollDALDto value = new PayrollDALDto();
		value.id = rs.getString("id");
		value.version = rs.getLong("version");

		value.contractId = rs.getString("contract_id");
		value.date = rs.getDate("date").toLocalDate();

		value.bonus = rs.getDouble("bonus");
		value.monthlyWage = rs.getDouble("monthlywage");
		value.productivityBonus = rs.getDouble("productivitybonus");
		value.trienniumPayment = rs.getDouble("trienniumpayment");

		value.incomeTax = rs.getDouble("incometax");
		value.nic = rs.getDouble("nic");
		return value;
	}

}
