package uo.ri.cws.application.business.vehicle.assembler;

import java.util.ArrayList;
import java.util.List;

import uo.ri.cws.application.business.vehicle.VehicleService.VehicleBLDto;
import uo.ri.cws.application.persistence.vehicle.VehicleGateway.VehicleDALDto;

public class VehicleAssembler {

	public static VehicleBLDto toBLDto(VehicleDALDto arg) {
		VehicleBLDto result = new VehicleBLDto();
		result.id = arg.id;
		result.version = arg.version;
		result.plateNumber = arg.platenumber;
		result.make = arg.make;
		result.model = arg.model;
		result.clientId = arg.client_id;
		result.vehicleTypeId = arg.vehicletype_id;
		return result;
	}

	public static List<VehicleBLDto> toBLDtoList(List<VehicleDALDto> arg) {
		List<VehicleBLDto> result = new ArrayList<>();
		for (VehicleDALDto mr : arg)
			result.add(toBLDto(mr));
		return result;
	}

}
