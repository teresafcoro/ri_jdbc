package uo.ri.cws.application.business;

import exception.NotYetImplementedException;
import uo.ri.cws.application.business.client.ClientService;
import uo.ri.cws.application.business.contract.ContractService;
import uo.ri.cws.application.business.contract.crud.ContractServiceImpl;
import uo.ri.cws.application.business.contracttype.ContractTypeService;
import uo.ri.cws.application.business.intervention.InterventionService;
import uo.ri.cws.application.business.invoice.InvoicingService;
import uo.ri.cws.application.business.invoice.create.InvoicingServiceImpl;
import uo.ri.cws.application.business.mechanic.MechanicService;
import uo.ri.cws.application.business.mechanic.crud.MechanicServiceImpl;
import uo.ri.cws.application.business.paymentmean.PaymentMeanService;
import uo.ri.cws.application.business.payroll.PayrollService;
import uo.ri.cws.application.business.payroll.create.PayrollServiceImpl;
import uo.ri.cws.application.business.professionalgroup.ProfessionalGroupService;
import uo.ri.cws.application.business.professionalgroup.crud.ProfessionalGroupServiceImpl;
import uo.ri.cws.application.business.sparepart.SparepartService;
import uo.ri.cws.application.business.vehicle.VehicleService;
import uo.ri.cws.application.business.vehicletype.VehicleTypeService;
import uo.ri.cws.application.business.workorder.WorkOrderService;

public class BusinessFactory {

	public static ClientService forClientService() {
		throw new NotYetImplementedException();
	}

	public static ContractService forContractService() {
		return new ContractServiceImpl();
	}

	public static ContractTypeService forContractTypeService() {
		throw new NotYetImplementedException();
	}

	public static InterventionService forInterventionService() {
		throw new NotYetImplementedException();
	}

	public static InvoicingService forInvoicingService() {
		return new InvoicingServiceImpl();
	}

	public static MechanicService forMechanicService() {
		return new MechanicServiceImpl();
	}

	public static PaymentMeanService forPaymentMeanService() {
		throw new NotYetImplementedException();
	}

	public static PayrollService forPayrollService() {
		return new PayrollServiceImpl();
	}

	public static ProfessionalGroupService forProfessionalGroupService() {
		return new ProfessionalGroupServiceImpl();
	}

	public static SparepartService forSparepartService() {
		throw new NotYetImplementedException();
	}

	public static VehicleService forVehicleService() {
		throw new NotYetImplementedException();
	}

	public static VehicleTypeService forVehicleTypeService() {
		throw new NotYetImplementedException();
	}

	public static WorkOrderService forWorkOrderService() {
		throw new NotYetImplementedException();
	}

}
