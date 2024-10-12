package uo.ri.cws.application.persistence.intervention.assembler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uo.ri.cws.application.persistence.intervention.InterventionGateway.InterventionDALDto;

public class InterventionAssembler {

	public static List<InterventionDALDto> toInterventionDALDtoList(
			ResultSet rs) throws SQLException {
		List<InterventionDALDto> res = new ArrayList<>();
		while (rs.next()) {
			res.add(resultSetToInterventionDALDto(rs));
		}

		return res;
	}

	private static InterventionDALDto resultSetToInterventionDALDto(
			ResultSet rs) throws SQLException {
		InterventionDALDto record = new InterventionDALDto();

		record.id = rs.getString("id");
		record.date = rs.getTimestamp("date").toLocalDateTime();
		record.minutes = rs.getInt("minutes");
		record.version = rs.getLong("version");
		record.mechanic_id = rs.getString("mechanic_Id");
		record.workorder_id = rs.getString("workorder_Id");

		return record;
	}

}
