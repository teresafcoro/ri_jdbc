package uo.ri.cws.application.persistence.client.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import jdbc.Jdbc;
import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.client.ClientGateway;
import uo.ri.cws.application.persistence.client.assembler.ClientAssembler;
import uo.ri.cws.application.persistence.util.Conf;

public class ClientGatewayImpl implements ClientGateway {

	@Override
	public void add(ClientDALDto t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(ClientDALDto t) {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<ClientDALDto> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClientDALDto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ClientDALDto> findByDni(String clientdni) {
		Optional<ClientDALDto> client;

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(
					Conf.getInstance().getProperty("TCLIENTS_FINDBYDNI"));

			pst.setString(1, clientdni);

			rs = pst.executeQuery();
			client = ClientAssembler.toClientDALDto(rs);
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

		return client;
	}

}
