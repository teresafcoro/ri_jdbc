package uo.ri.cws.application.ui.util;

import java.util.List;

import console.Console;
import uo.ri.cws.application.business.invoice.InvoicingService.InvoiceBLDto;
import uo.ri.cws.application.business.invoice.InvoicingService.WorkOrderForInvoicingBLDto;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.business.payroll.PayrollService.PayrollBLDto;
import uo.ri.cws.application.business.payroll.PayrollService.PayrollSummaryBLDto;
import uo.ri.cws.application.business.professionalgroup.ProfessionalGroupService.ProfessionalGroupBLDto;

public class Printer {

	public static void printMechanic(MechanicBLDto m) {
		Console.printf("\t%-36.36s %-9s %-10.10s %-25.25s %-10.2s\n", m.id,
				m.dni, m.name, m.surname, m.version);
	}

	public static void printMechanics(List<MechanicBLDto> list) {
		Console.printf("\t%-36s %-9s %-10s %-25s %-10s\n",
				"Mechanic identifier", "DNI", "Name", "Surname", "Version");
		for (MechanicBLDto m : list)
			printMechanic(m);
	}

	public static void printInvoice(InvoiceBLDto invoice) {
		double importeConIVa = invoice.total;
		double iva = invoice.vat;
		double importeSinIva = importeConIVa / (1 + iva / 100);

		Console.printf("Invoice number: %d%n", invoice.number);
		Console.printf("\tDate: %1$td/%1$tm/%1$tY%n", invoice.date);
		Console.printf("\tAmount: %.2f %n", importeSinIva);
		Console.printf("\tVat: %.1f %% %n", invoice.vat);
		Console.printf("\tTotal (vat included): %.2f %n", invoice.total);
		Console.printf("\tState: %s%n", invoice.state);
	}

	public static void printInvoicingWorkOrder(WorkOrderForInvoicingBLDto arg) {
		Console.printf("\t%s \t%-40.40s \t%s \t%-12.12s \t%.2f\n", arg.id,
				arg.description, arg.date, arg.state, arg.total);
	}

	public static void printInvoicingWorkOrders(
			List<WorkOrderForInvoicingBLDto> arg) {
		Console.printf("\t%s \t%-40.40s \t%s \t%-12.12s \t%.2f\n", "Identifier",
				"description", "state", "total");
		for (WorkOrderForInvoicingBLDto inv : arg)
			printInvoicingWorkOrder(inv);
	}

	public static void printProfessionalGroup(ProfessionalGroupBLDto arg) {
		Console.printf("\t%-36.36s %-10s %-10.10s %.2f %.2f\n", arg.id,
				arg.version, arg.name, arg.trieniumSalary,
				arg.productivityRate);
	}

	public static void printProfessionalGroups(
			List<ProfessionalGroupBLDto> list) {
		Console.printf("\t%-36s %-9s %-10s %-25s %-10s\n", "id", "version",
				"name", "trienniumSalary", "productivityRate");
		for (ProfessionalGroupBLDto pg : list)
			printProfessionalGroup(pg);
	}

	public static void printPayrollSummary(PayrollSummaryBLDto arg) {
		Console.printf("\t%-36.36s %-10s %-10.10s %.2f %.2f\n", arg.id,
				arg.version, arg.date, arg.netWage);
	}

	public static void printPayrollsSummary(List<PayrollSummaryBLDto> list) {
		Console.printf("\t%-36s %-9s %-10s %-25s\n", "id", "version", "date",
				"netWage");
		for (PayrollSummaryBLDto p : list)
			printPayrollSummary(p);
	}

	public static void printPayrolls(PayrollBLDto arg) {
		Console.printf(
				"\t%-36.36s %-10s %-36s %s %.2f %.2f %.2f %.2f %.2f %.2f %.2f\n",
				arg.id, arg.version, arg.contractId, arg.date, arg.monthlyWage,
				arg.bonus, arg.productivityBonus, arg.trienniumPayment,
				arg.incomeTax, arg.nic, arg.netWage);
	}

	public static void printMechanicsInForceInProfessionalGroup(
			List<MechanicBLDto> list) {
		printMechanics(list);
		Console.printf("\t%-36.36s\n", "N� de trabajadores");
		Console.printf("\t%-9s\n", list.size());
	}

}