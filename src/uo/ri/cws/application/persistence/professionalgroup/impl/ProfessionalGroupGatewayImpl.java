package uo.ri.cws.application.persistence.professionalgroup.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import jdbc.Jdbc;
import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.professionalgroup.ProfessionalGroupGateway;
import uo.ri.cws.application.persistence.professionalgroup.assembler.ProfessionalGroupAssembler;
import uo.ri.cws.application.persistence.util.Conf;

public class ProfessionalGroupGatewayImpl implements ProfessionalGroupGateway {

	@Override
	public void add(ProfessionalGroupDALDto pg) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(
					Conf.getInstance().getProperty("TPROFESSIONALGROUPS_ADD"));

			pst.setString(1, pg.id);
			pst.setString(2, pg.name);
			pst.setDouble(3, pg.productivityBonusPercentage);
			pst.setDouble(4, pg.trienniumPayment);
			pst.setLong(5, pg.version);

			pst.executeUpdate();
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
	}

	@Override
	public void remove(String id) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance()
					.getProperty("TPROFESSIONALGROUPS_REMOVE"));

			pst.setString(1, id);

			pst.executeUpdate();
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
	}

	@Override
	public void update(ProfessionalGroupDALDto pg) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance()
					.getProperty("TPROFESSIONALGROUPS_UPDATE"));

			pst.setDouble(1, pg.productivityBonusPercentage);
			pst.setDouble(2, pg.trienniumPayment);
			pst.setString(3, pg.name);

			pst.executeUpdate();
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
	}

	@Override
	public Optional<ProfessionalGroupDALDto> findById(String id) {
		Optional<ProfessionalGroupDALDto> pg;

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance()
					.getProperty("TPROFESSIONALGROUPS_FINDBYID"));

			pst.setString(1, id);

			rs = pst.executeQuery();
			pg = ProfessionalGroupAssembler.toProfessionalGroupDALDto(rs);
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

		return pg;
	}

	@Override
	public List<ProfessionalGroupDALDto> findAll() {
		List<ProfessionalGroupDALDto> pgs;

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance()
					.getProperty("TPROFESSIONALGROUPS_FINDALL"));

			rs = pst.executeQuery();
			pgs = ProfessionalGroupAssembler.toProfessionalGroupDALDtoList(rs);
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

		return pgs;
	}

	@Override
	public Optional<ProfessionalGroupDALDto> findByName(String name) {
		Optional<ProfessionalGroupDALDto> pg;

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance()
					.getProperty("TPROFESSIONALGROUPS_FINDBYNAME"));

			pst.setString(1, name);

			rs = pst.executeQuery();
			pg = ProfessionalGroupAssembler.toProfessionalGroupDALDto(rs);
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

		return pg;
	}

}
