package uo.ri.cws.application.persistence.contract;

import java.time.LocalDate;
import java.util.List;

import uo.ri.cws.application.persistence.Gateway;
import uo.ri.cws.application.persistence.contract.ContractGateway.ContractDALDto;

public interface ContractGateway extends Gateway<ContractDALDto> {

	/*
	 * Finds contracts by mechanic id
	 * 
	 * @param id, mechanic id
	 * 
	 * @return contracts, list of valid contracts
	 */
	List<ContractDALDto> findByMechanic(String id);

	/*
	 * Finds contracts by professional group id
	 * 
	 * @param id, professional group id
	 * 
	 * @return contracts, list of valid contracts
	 */
	List<ContractDALDto> findByProfessionalGroupId(String id);

	/*
	 * Finds all the contracts in force
	 * 
	 * @return contracts, list of contracts in force
	 */
	List<ContractDALDto> findInForce();

	/*
	 * Finds all valid contracts
	 * 
	 * A valid contract is a contract in force or with endDate this month
	 * 
	 * @return contracts, list of valid contracts
	 */
	List<ContractDALDto> findValidContracts();

	/*
	 * Finds all in force contracts in professional group
	 * 
	 * @param id, professional group id
	 * 
	 * @return contracts, list of contracts
	 */
	List<ContractDALDto> findInForceByProfessionalGroupId(String id);

	public class ContractDALDto {
		public String id;
		public long version;

		public LocalDate startDate;
		public LocalDate endDate;
		public double annualBaseWage;
		public double settlement;
		public String state; // ContractState = { TERMINATED, IN_FORCE }

		public String contractType_id;
		public String mechanic_id;
		public String professionalGroup_id;
	}

}
