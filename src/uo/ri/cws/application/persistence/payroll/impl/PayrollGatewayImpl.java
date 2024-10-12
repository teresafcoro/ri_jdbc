package uo.ri.cws.application.persistence.payroll.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import jdbc.Jdbc;
import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.payroll.PayrollGateway;
import uo.ri.cws.application.persistence.payroll.assembler.PayrollAssembler;
import uo.ri.cws.application.persistence.util.Conf;

public class PayrollGatewayImpl implements PayrollGateway {

	@Override
	public void add(PayrollDALDto payrollDALDto) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(
					Conf.getInstance().getProperty("TPAYROLLS_ADD"));

			pst.setString(1, payrollDALDto.id);
			pst.setDouble(2, payrollDALDto.bonus);
			pst.setDate(3, Date.valueOf(payrollDALDto.date));
			pst.setDouble(4, payrollDALDto.incomeTax);
			pst.setDouble(5, payrollDALDto.monthlyWage);
			pst.setDouble(6, payrollDALDto.nic);
			pst.setDouble(7, payrollDALDto.productivityBonus);
			pst.setDouble(8, payrollDALDto.trienniumPayment);
			pst.setLong(9, payrollDALDto.version);
			pst.setString(10, payrollDALDto.contractId);

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

			pst = c.prepareStatement(
					Conf.getInstance().getProperty("TPAYROLLS_REMOVE"));

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
	public void update(PayrollDALDto t) {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<PayrollDALDto> findById(String id) {
		Optional<PayrollDALDto> payroll;

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(
					Conf.getInstance().getProperty("TPAYROLLS_FINDBYID"));

			pst.setString(1, id);

			rs = pst.executeQuery();
			payroll = PayrollAssembler.toPayrollDALDto(rs);
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

		return payroll;
	}

	@Override
	public List<PayrollDALDto> findAll() {
		List<PayrollDALDto> payrolls;

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(
					Conf.getInstance().getProperty("TPAYROLLS_FINDALL"));

			rs = pst.executeQuery();
			payrolls = PayrollAssembler.toPayrollDALDtoList(rs);
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

		return payrolls;
	}

	@Override
	public Optional<PayrollDALDto> findByContract(String contractId) {
		Optional<PayrollDALDto> payroll;

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(
					Conf.getInstance().getProperty("TPAYROLLS_FINDBYCONTRACT"));

			pst.setString(1, contractId);

			rs = pst.executeQuery();
			payroll = PayrollAssembler.toPayrollDALDto(rs);
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

		return payroll;
	}

	@Override
	public List<PayrollDALDto> findAllGeneratedThisMonth() {
		List<PayrollDALDto> payrolls;

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance()
					.getProperty("TPAYROLLS_FINDALLGENERATEDTHISMONTH"));

			pst.setInt(1, LocalDate.now().getYear());
			pst.setInt(2, LocalDate.now().getMonthValue());

			rs = pst.executeQuery();
			payrolls = PayrollAssembler.toPayrollDALDtoList(rs);
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

		return payrolls;
	}

	@Override
	public List<PayrollDALDto> findAllGeneratedThisMonthFor(String contractId) {
		List<PayrollDALDto> payrolls;

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance()
					.getProperty("TPAYROLLS_FINDALLGENERATEDTHISMONTHFOR"));

			pst.setString(1, contractId);
			pst.setInt(2, LocalDate.now().getYear());
			pst.setInt(3, LocalDate.now().getMonthValue());

			rs = pst.executeQuery();
			payrolls = PayrollAssembler.toPayrollDALDtoList(rs);
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

		return payrolls;
	}

}
