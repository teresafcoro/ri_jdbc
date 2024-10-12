package uo.ri.cws.application.persistence.contract.assembler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uo.ri.cws.application.persistence.contract.ContractGateway.ContractDALDto;

public class ContractAssembler {

	public static List<ContractDALDto> toContractDALDtoList(ResultSet rs)
			throws SQLException {
		List<ContractDALDto> res = new ArrayList<>();
		while (rs.next()) {
			res.add(resultSetToContractDALDto(rs));
		}

		return res;
	}

	private static ContractDALDto resultSetToContractDALDto(ResultSet rs)
			throws SQLException {
		ContractDALDto record = new ContractDALDto();

		record.id = rs.getString("id");
		record.startDate = rs.getDate("startDate").toLocalDate();
		// record.endDate = rs.getDate("endDate").toLocalDate();
		record.annualBaseWage = rs.getDouble("annualBaseWage");
		record.settlement = rs.getDouble("settlement");
		record.version = rs.getLong("version");
		record.state = rs.getString("state");
		record.mechanic_id = rs.getString("mechanic_Id");
		record.contractType_id = rs.getString("contractType_id");
		record.professionalGroup_id = rs.getString("professionalGroup_id");

		return record;
	}

}
