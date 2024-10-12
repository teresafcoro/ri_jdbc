package uo.ri.cws.application.persistence.contracttype;

import uo.ri.cws.application.persistence.Gateway;
import uo.ri.cws.application.persistence.contracttype.ContractTypeGateway.ContractTypeDALDto;

public interface ContractTypeGateway extends Gateway<ContractTypeDALDto> {

	public class ContractTypeDALDto {
		public String id;
		public Long version;
		public String name;
		public double compensationDays;
	}

}
