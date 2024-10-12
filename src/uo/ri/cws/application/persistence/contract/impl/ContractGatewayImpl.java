package uo.ri.cws.application.persistence.contract.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import jdbc.Jdbc;
import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.contract.ContractGateway;
import uo.ri.cws.application.persistence.contract.assembler.ContractAssembler;
import uo.ri.cws.application.persistence.util.Conf;

public class ContractGatewayImpl implements ContractGateway {

	@Override
	public void add(ContractDALDto t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(ContractDALDto t) {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<ContractDALDto> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ContractDALDto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ContractDALDto> findByMechanic(String id) {
		List<ContractDALDto> contracts;

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance()
					.getProperty("TCONTRACTS_FINDBYMECHANIC"));

			pst.setString(1, id);

			rs = pst.executeQuery();
			contracts = ContractAssembler.toContractDALDtoList(rs);
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

		return contracts;
	}

	@Override
	public List<ContractDALDto> findByProfessionalGroupId(String id) {
		List<ContractDALDto> contracts;

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance()
					.getProperty("TCONTRACTS_FINDBYPROFESSIONALGROUPID"));

			pst.setString(1, id);

			rs = pst.executeQuery();
			contracts = ContractAssembler.toContractDALDtoList(rs);
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

		return contracts;
	}

	@Override
	public List<ContractDALDto> findInForce() {
		List<ContractDALDto> contracts;

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(
					Conf.getInstance().getProperty("TCONTRACTS_FINDINFORCE"));

			rs = pst.executeQuery();
			contracts = ContractAssembler.toContractDALDtoList(rs);
		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					/* ignore */ }
			if (pst != null)
				try {
					pst.close();
				} catch (SQLException e) {
					/* ignore */ }
		}

		return contracts;
	}

	@Override
	public List<ContractDALDto> findValidContracts() {
		List<ContractDALDto> contracts;

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(
					Conf.getInstance().getProperty("TCONTRACTS_FINDVALID"));

			pst.setInt(1, LocalDate.now().getYear());
			pst.setInt(2, LocalDate.now().getMonthValue());

			rs = pst.executeQuery();
			contracts = ContractAssembler.toContractDALDtoList(rs);
		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					/* ignore */ }
			if (pst != null)
				try {
					pst.close();
				} catch (SQLException e) {
					/* ignore */ }
		}

		return contracts;
	}

	@Override
	public List<ContractDALDto> findInForceByProfessionalGroupId(String id) {
		List<ContractDALDto> contracts;

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty(
					"TCONTRACTS_FINDINFORCEBYPROFESSIONALGROUPID"));

			pst.setString(1, id);

			rs = pst.executeQuery();
			contracts = ContractAssembler.toContractDALDtoList(rs);
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

		return contracts;
	}

}
