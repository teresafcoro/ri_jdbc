package uo.ri.cws.application.business.intervention;

import java.time.LocalDateTime;

public interface InterventionService {

	// ...

	public class InterventionBLDto {
		public String id;
		public Long version;

		public LocalDateTime date;
		public int minutes;

		public String mechanic_id;
		public String workorder_id;
	}

	public class SubstitutionBLDto {
		public int quantity;
		public String sparepart_id;
		public String intervention_id;
	}

}
