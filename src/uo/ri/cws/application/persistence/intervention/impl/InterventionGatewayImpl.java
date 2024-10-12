package uo.ri.cws.application.persistence.intervention.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import jdbc.Jdbc;
import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.intervention.InterventionGateway;
import uo.ri.cws.application.persistence.intervention.assembler.InterventionAssembler;
import uo.ri.cws.application.persistence.util.Conf;

public class InterventionGatewayImpl implements InterventionGateway {

	@Override
	public void add(InterventionDALDto t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(InterventionDALDto t) {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<InterventionDALDto> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InterventionDALDto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InterventionDALDto> findByMechanic(String id) {
		List<InterventionDALDto> interventions;

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance()
					.getProperty("TINTERVENTIONS_FINDBYMECHANIC"));

			pst.setString(1, id);

			rs = pst.executeQuery();
			interventions = InterventionAssembler.toInterventionDALDtoList(rs);
		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					/* ignore */
				}
			if (pst != null)
				try {
					pst.close();
				} catch (SQLException e) {
					/* ignore */
				}
		}

		return interventions;
	}

}
