package uo.ri.cws.application.ui.manager.action.mechanicManagement;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.ui.util.Printer;

public class FindMechanicsWithContractInForceInProfessionalGroupsAction
		implements
			Action {

	@Override
	public void execute() throws Exception {
		String name = Console.readString("Professional group name ");

		Console.println("\nList of mechanics in force in professional group\n");
		Printer.printMechanicsInForceInProfessionalGroup(BusinessFactory
				.forMechanicService().findMechanicsInProfessionalGroups(name));
	}

}
