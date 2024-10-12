package uo.ri.cws.application.persistence.intervention;

import java.time.LocalDateTime;
import java.util.List;

import uo.ri.cws.application.persistence.Gateway;
import uo.ri.cws.application.persistence.intervention.InterventionGateway.InterventionDALDto;

public interface InterventionGateway extends Gateway<InterventionDALDto> {

	/*
	 * Finds interventions by mechanic id
	 * 
	 * @param id, mechanic id
	 * 
	 * @return interventions, list of interventions
	 */
	List<InterventionDALDto> findByMechanic(String id);

	public class InterventionDALDto {
		public String id;
		public Long version;

		public LocalDateTime date;
		public int minutes;

		public String mechanic_id;
		public String workorder_id;
	}

	public class SubstitutionDALDto {
		public int quantity;
		public String sparepart_id;
		public String intervention_id;
	}

}
