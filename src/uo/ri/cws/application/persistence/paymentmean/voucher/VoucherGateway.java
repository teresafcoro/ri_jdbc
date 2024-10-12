package uo.ri.cws.application.persistence.paymentmean.voucher;

import uo.ri.cws.application.persistence.Gateway;
import uo.ri.cws.application.persistence.paymentmean.PaymentMeanGateway.PaymentMeanDALDto;
import uo.ri.cws.application.persistence.paymentmean.voucher.VoucherGateway.VoucherDALDto;

public interface VoucherGateway extends Gateway<VoucherDALDto> {

	public class VoucherDALDto extends PaymentMeanDALDto {
		public String code;
		public String description;
		public Double balance;
	}

	/**
	 * An aggregated result of all vouchers of a client
	 */
	public class VoucherSummaryDALDto {
		public String dni; // of the client
		public String name; // of the client
		public String surname; // of the client
		public int issued; // how many vouchers has been issued
		public double totalAmount; // the total amount "voucherized" (money)
		public double availableBalance; // how much remains available for
		// the
		// client
		public double consumed; // how much has been
	}

}
