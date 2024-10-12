package uo.ri.cws.application.ui.manager.action.mechanicManagement;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.ui.util.Printer;

public class FindMechanicsInProfessionalGroupsAction implements Action {

	@Override
	public void execute() throws BusinessException {
		String name = Console.readString("Professional group name ");

		Console.println("\nList of mechanics in professional group\n");
		Printer.printMechanics(BusinessFactory.forMechanicService()
				.findMechanicsInProfessionalGroups(name));
	}

}
