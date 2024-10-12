package uo.ri.cws.application.persistence.professionalgroup;

import java.util.Optional;

import uo.ri.cws.application.persistence.Gateway;
import uo.ri.cws.application.persistence.professionalgroup.ProfessionalGroupGateway.ProfessionalGroupDALDto;

public interface ProfessionalGroupGateway
		extends
			Gateway<ProfessionalGroupDALDto> {

	/*
	 * Find professional group by name
	 * 
	 * @param name, professional group name
	 * 
	 * @return a professional group or null if it does not exists
	 */
	public Optional<ProfessionalGroupDALDto> findByName(String name);

	public class ProfessionalGroupDALDto {
		public String id;
		public long version;

		public String name;
		public double trienniumPayment;
		public double productivityBonusPercentage;
	}

}
