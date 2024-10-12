package uo.ri.cws.application.business.professionalgroup.assembler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.professionalgroup.ProfessionalGroupService.ProfessionalGroupBLDto;
import uo.ri.cws.application.persistence.professionalgroup.ProfessionalGroupGateway.ProfessionalGroupDALDto;

public class ProfessionalGroupAssembler {

	public static ProfessionalGroupBLDto toBLDto(ProfessionalGroupDALDto dto) {
		ProfessionalGroupBLDto result = new ProfessionalGroupBLDto();
		result.id = dto.id;
		result.name = dto.name;
		result.productivityRate = dto.productivityBonusPercentage;
		result.trieniumSalary = dto.trienniumPayment;
		result.version = dto.version;
		return result;
	}

	public static ProfessionalGroupDALDto toDALDto(ProfessionalGroupBLDto dto) {
		ProfessionalGroupDALDto result = new ProfessionalGroupDALDto();
		result.id = dto.id;
		result.name = dto.name;
		result.productivityBonusPercentage = dto.productivityRate;
		result.trienniumPayment = dto.trieniumSalary;
		result.version = dto.version;
		return result;
	}

	public static List<ProfessionalGroupBLDto> toBLDtoList(
			List<ProfessionalGroupDALDto> pgs) {
		List<ProfessionalGroupBLDto> result = new ArrayList<>();
		for (ProfessionalGroupDALDto pg : pgs)
			result.add(toBLDto(pg));
		return result;
	}

	public static Optional<ProfessionalGroupBLDto> toOptionalBLDto(
			Optional<ProfessionalGroupDALDto> arg) {
		Optional<ProfessionalGroupBLDto> result = arg.isEmpty()
				? Optional.ofNullable(null)
				: Optional.ofNullable(toBLDto(arg.get()));
		return result;
	}

}
