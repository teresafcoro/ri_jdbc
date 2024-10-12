package uo.ri.cws.application.business.client.assembler;

import java.util.Optional;

import uo.ri.cws.application.business.client.ClientService.ClientBLDto;
import uo.ri.cws.application.persistence.client.ClientGateway.ClientDALDto;

public class ClientAssembler {

	public static ClientBLDto toBLDto(ClientDALDto arg) {
		ClientBLDto result = new ClientBLDto();
		result.id = arg.id;
		result.version = arg.version;
		result.dni = arg.dni;
		result.name = arg.name;
		result.surname = arg.surname;
		result.phone = arg.phone;
		result.email = arg.email;
		result.addressStreet = arg.street;
		result.addressCity = arg.city;
		result.addressZipcode = arg.zipcode;
		return result;
	}

	public static Optional<ClientBLDto> toOptionalBLDto(
			Optional<ClientDALDto> arg) {
		Optional<ClientBLDto> client = arg.isEmpty()
				? Optional.ofNullable(null)
				: Optional.ofNullable(toBLDto(arg.get()));
		return client;
	}

}
