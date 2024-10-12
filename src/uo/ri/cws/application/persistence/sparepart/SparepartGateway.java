package uo.ri.cws.application.persistence.sparepart;

import uo.ri.cws.application.persistence.Gateway;
import uo.ri.cws.application.persistence.sparepart.SparepartGateway.SparepartDALDto;

public interface SparepartGateway extends Gateway<SparepartDALDto> {

	public class SparepartDALDto {
		public String id;
		public long version;

		public String code;
		public String description;
		public double price;
	}

}
