package uo.ri.cws.application.persistence.vehicletype;

import uo.ri.cws.application.persistence.Gateway;
import uo.ri.cws.application.persistence.vehicletype.VehicleTypeGateway.VehicleTypeDALDto;

public interface VehicleTypeGateway extends Gateway<VehicleTypeDALDto> {

	public class VehicleTypeDALDto {
		public String id;
		public long version;

		public String name;
		public double pricePerHour;
		public int minTrainigHours;
	}

}
