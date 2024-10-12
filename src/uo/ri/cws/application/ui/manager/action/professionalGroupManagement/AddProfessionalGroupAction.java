package uo.ri.cws.application.ui.manager.action.professionalGroupManagement;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.professionalgroup.ProfessionalGroupService.ProfessionalGroupBLDto;
import uo.ri.cws.application.ui.util.Printer;

public class AddProfessionalGroupAction implements Action {

	@Override
	public void execute() throws Exception {
		// Get info
		String name = Console.readString("Name ");
		Double triennium = Console.readDouble("Triennium Salary ");
		Double productivityRate = Console.readDouble("Productivity rate ");

		// Process
		ProfessionalGroupBLDto pg = new ProfessionalGroupBLDto();
		pg.name = name;
		pg.trieniumSalary = triennium;
		pg.productivityRate = productivityRate;

		ProfessionalGroupBLDto result = BusinessFactory
				.forProfessionalGroupService().addProfessionalGroup(pg);

		// Print result
		Console.println("New Professional Group succesfully added");
		Printer.printProfessionalGroup(result);
	}

}
