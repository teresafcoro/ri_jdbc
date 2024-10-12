package uo.ri.cws.application.persistence.workorder.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jdbc.Jdbc;
import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.util.Conf;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway;
import uo.ri.cws.application.persistence.workorder.assembler.WorkOrderAssembler;

public class WorkOrderGatewayImpl implements WorkOrderGateway {

	@Override
	public void add(WorkOrderDALDto t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(WorkOrderDALDto workorder) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(
					Conf.getInstance().getProperty("TWORKORDERS_UPDATE"));

			pst.setString(1, workorder.invoice_id);
			pst.setString(2, workorder.id);

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
	public Optional<WorkOrderDALDto> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WorkOrderDALDto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WorkOrderDALDto> findByMechanic(String id) {
		List<WorkOrderDALDto> workorders;

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance()
					.getProperty("TWORKORDERS_FINDBYMECHANIC"));

			pst.setString(1, id);

			rs = pst.executeQuery();
			workorders = WorkOrderAssembler.toWorkOrderDALDtoList(rs);
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

		return workorders;
	}

	@Override
	public List<WorkOrderDALDto> findNotInvoicedForVehicles(
			List<String> vehicleIds) {
		List<WorkOrderDALDto> workorders;

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance()
					.getProperty("TWORKORDERS_FINDNOTINVOICEDFORVEHICLES"));

			for (String id : vehicleIds) {
				pst.setString(1, id);
				rs = pst.executeQuery();
			}

			workorders = WorkOrderAssembler.toWorkOrderDALDtoList(rs);
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

		return workorders;
	}

	@Override
	public List<WorkOrderDALDto> findByVehicleId(String vehicleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WorkOrderDALDto> findByIds(List<String> workOrderIDS) {
		List<WorkOrderDALDto> workorders = new ArrayList<>();

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(
					Conf.getInstance().getProperty("TWORKORDERS_FINDBYIDS"));

			for (String workOrderID : workOrderIDS) {
				pst.setString(1, workOrderID);

				rs = pst.executeQuery();
			}

			workorders = WorkOrderAssembler.toWorkOrderDALDtoList(rs);
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

		return workorders;
	}

	@Override
	public List<WorkOrderDALDto> findByInvoice(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WorkOrderDALDto> findInvoiced() {
		// TODO Auto-generated method stub
		return null;
	}

}
