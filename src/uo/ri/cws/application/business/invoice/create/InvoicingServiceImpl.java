package uo.ri.cws.application.business.invoice.create;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.invoice.InvoicingService;
import uo.ri.cws.application.business.invoice.create.commands.CreateInvoiceTS;
import uo.ri.cws.application.business.invoice.create.commands.FindNotInvoicedWorkOrdersTS;
import uo.ri.cws.application.business.util.CommandExecutor;

public class InvoicingServiceImpl implements InvoicingService {

	@Override
	public InvoiceBLDto createInvoiceFor(List<String> workOrderIds)
			throws BusinessException {
		return new CommandExecutor().execute(new CreateInvoiceTS(workOrderIds));
	}

	@Override
	public List<WorkOrderForInvoicingBLDto> findWorkOrdersByClientDni(
			String dni) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WorkOrderForInvoicingBLDto> findNotInvoicedWorkOrdersByClientDni(
			String dni) throws BusinessException {
		return new CommandExecutor()
				.execute(new FindNotInvoicedWorkOrdersTS(dni));
	}

	@Override
	public List<WorkOrderForInvoicingBLDto> findWorkOrdersByPlateNumber(
			String plate) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<InvoiceBLDto> findInvoiceByNumber(Long number)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PaymentMeanForInvoicingBLDto> findPayMeansByClientDni(
			String dni) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void settleInvoice(String invoiceId, List<Charge_BLDto> charges)
			throws BusinessException {
		// TODO Auto-generated method stub
	}

}
