package uo.ri.cws.application.persistence;

import uo.ri.cws.application.persistence.client.ClientGateway;
import uo.ri.cws.application.persistence.client.impl.ClientGatewayImpl;
import uo.ri.cws.application.persistence.contract.ContractGateway;
import uo.ri.cws.application.persistence.contract.impl.ContractGatewayImpl;
import uo.ri.cws.application.persistence.contracttype.ContractTypeGateway;
import uo.ri.cws.application.persistence.contracttype.impl.ContractTypeGatewayImpl;
import uo.ri.cws.application.persistence.intervention.InterventionGateway;
import uo.ri.cws.application.persistence.intervention.impl.InterventionGatewayImpl;
import uo.ri.cws.application.persistence.invoice.InvoiceGateway;
import uo.ri.cws.application.persistence.invoice.impl.InvoiceGatewayImpl;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;
import uo.ri.cws.application.persistence.mechanic.impl.MechanicGatewayImpl;
import uo.ri.cws.application.persistence.paymentmean.PaymentMeanGateway;
import uo.ri.cws.application.persistence.paymentmean.impl.PaymentMeanGatewayImpl;
import uo.ri.cws.application.persistence.paymentmean.voucher.VoucherGateway;
import uo.ri.cws.application.persistence.paymentmean.voucher.impl.VoucherGatewayImpl;
import uo.ri.cws.application.persistence.payroll.PayrollGateway;
import uo.ri.cws.application.persistence.payroll.impl.PayrollGatewayImpl;
import uo.ri.cws.application.persistence.professionalgroup.ProfessionalGroupGateway;
import uo.ri.cws.application.persistence.professionalgroup.impl.ProfessionalGroupGatewayImpl;
import uo.ri.cws.application.persistence.sparepart.SparepartGateway;
import uo.ri.cws.application.persistence.sparepart.impl.SparepartGatewayImpl;
import uo.ri.cws.application.persistence.vehicle.VehicleGateway;
import uo.ri.cws.application.persistence.vehicle.impl.VehicleGatewayImpl;
import uo.ri.cws.application.persistence.vehicletype.VehicleTypeGateway;
import uo.ri.cws.application.persistence.vehicletype.impl.VehicleTypeGatewayImpl;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway;
import uo.ri.cws.application.persistence.workorder.impl.WorkOrderGatewayImpl;

public class PersistenceFactory {

	public static ClientGateway forClient() {
		return new ClientGatewayImpl();
	}

	public static ContractGateway forContract() {
		return new ContractGatewayImpl();
	}

	public static ContractTypeGateway forContractType() {
		return new ContractTypeGatewayImpl();
	}

	public static InterventionGateway forIntervention() {
		return new InterventionGatewayImpl();
	}

	public static InvoiceGateway forInvoice() {
		return new InvoiceGatewayImpl();
	}

	public static MechanicGateway forMechanic() {
		return new MechanicGatewayImpl();
	}

	public static PaymentMeanGateway forPaymentMean() {
		return new PaymentMeanGatewayImpl();
	}

	public static PayrollGateway forPayroll() {
		return new PayrollGatewayImpl();
	}

	public static ProfessionalGroupGateway forProfessionalGroup() {
		return new ProfessionalGroupGatewayImpl();
	}

	public static SparepartGateway forSparepart() {
		return new SparepartGatewayImpl();
	}

	public static VehicleGateway forVehicle() {
		return new VehicleGatewayImpl();
	}

	public static VehicleTypeGateway forVehicleType() {
		return new VehicleTypeGatewayImpl();
	}

	public static VoucherGateway forVoucher() {
		return new VoucherGatewayImpl();
	}

	public static WorkOrderGateway forWorkOrder() {
		return new WorkOrderGatewayImpl();
	}

}
