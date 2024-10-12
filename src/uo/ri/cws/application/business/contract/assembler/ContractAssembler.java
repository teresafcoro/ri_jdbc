package uo.ri.cws.application.business.contract.assembler;

import java.util.ArrayList;
import java.util.List;

import uo.ri.cws.application.business.contract.ContractService.ContractBLDto;
import uo.ri.cws.application.business.contract.ContractService.ContractState;
import uo.ri.cws.application.persistence.contract.ContractGateway.ContractDALDto;

public class ContractAssembler {

	public static ContractBLDto toBLDto(ContractDALDto arg) {
		ContractBLDto result = new ContractBLDto();
		result.id = arg.id;
		result.version = arg.version;

		result.dni = arg.mechanic_id;
		result.contractTypeName = arg.contractType_id;
		result.professionalGroupName = arg.professionalGroup_id;
		result.startDate = arg.startDate;
		result.endDate = arg.endDate;
		result.annualBaseWage = arg.annualBaseWage;
		result.settlement = arg.settlement;
		result.state = ContractState.valueOf(arg.state);
		return result;
	}

	public static List<ContractBLDto> toBLDtos(List<ContractDALDto> arg) {
		List<ContractBLDto> result = new ArrayList<ContractBLDto>();
		for (ContractDALDto c : arg)
			result.add(toBLDto(c));
		return result;
	}

}
