package uo.ri.cws.application.persistence.workorder.assembler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.persistence.workorder.WorkOrderGateway.WorkOrderDALDto;

public class WorkOrderAssembler {

	public static Optional<WorkOrderDALDto> toWorkOrderDALDto(ResultSet rs)
			throws SQLException {
		WorkOrderDALDto record = null;

		if (rs.next()) {
			record = resultSetToWorkOrderDALDto(rs);
		}
		return Optional.ofNullable(record);

	}

	public static List<WorkOrderDALDto> toWorkOrderDALDtoList(ResultSet rs)
			throws SQLException {
		List<WorkOrderDALDto> res = new ArrayList<>();
		while (rs.next()) {
			res.add(resultSetToWorkOrderDALDto(rs));
		}

		return res;
	}

	private static WorkOrderDALDto resultSetToWorkOrderDALDto(ResultSet rs)
			throws SQLException {
		WorkOrderDALDto record = new WorkOrderDALDto();

		record.id = rs.getString("id");
		record.version = rs.getLong("version");

		record.vehicle_id = rs.getString("vehicle_id");
		record.description = rs.getString("description");
		record.date = rs.getTimestamp("date").toLocalDateTime();
		record.amount = rs.getDouble("amount");
		record.state = rs.getString("state");
		record.mechanic_id = rs.getString("mechanic_id");
		record.invoice_id = rs.getString("invoice_id");
		record.usedforvoucher = false;
		return record;
	}

}
