package uo.ri.cws.application.persistence.vehicle.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import jdbc.Jdbc;
import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.util.Conf;
import uo.ri.cws.application.persistence.vehicle.VehicleGateway;
import uo.ri.cws.application.persistence.vehicle.assembler.VehicleAssembler;

public class VehicleGatewayImpl implements VehicleGateway {

	@Override
	public void add(VehicleDALDto t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(VehicleDALDto t) {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<VehicleDALDto> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VehicleDALDto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VehicleDALDto> findByClient(String id) {
		List<VehicleDALDto> vehicles;

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(
					Conf.getInstance().getProperty("TVEHICLES_FINDBYCLIENT"));

			pst.setString(1, id);

			rs = pst.executeQuery();
			vehicles = VehicleAssembler.toVehicleDALDtoList(rs);
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

		return vehicles;
	}

}
