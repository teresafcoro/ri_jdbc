package uo.ri.cws.application.persistence.paymentmean;

import java.time.LocalDate;

import uo.ri.cws.application.persistence.Gateway;
import uo.ri.cws.application.persistence.paymentmean.PaymentMeanGateway.PaymentMeanDALDto;

public interface PaymentMeanGateway extends Gateway<PaymentMeanDALDto> {

	public abstract class PaymentMeanDALDto {
		public String id;
		public long version;

		public String clientId;
		public double accumulated;
	}

	public class Cash_DALDto extends PaymentMeanDALDto {

	}

	public class Card_DALDto extends PaymentMeanDALDto {
		public String cardNumber;
		public LocalDate cardExpiration;
		public String cardType;

	}
}
