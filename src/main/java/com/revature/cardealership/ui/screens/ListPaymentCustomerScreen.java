package com.revature.cardealership.ui.screens;

import java.io.IOException;
import java.util.Iterator;

import com.revature.cardealership.exceptions.NotFoundRecordException;
import com.revature.cardealership.model.Payment;
import com.revature.cardealership.services.ContractService;
import com.revature.cardealership.utils.InputUtil;
import com.revature.cardealership.utils.ServiceUtil;

public class ListPaymentCustomerScreen implements Screen {

	private ContractService contractService;

	private Screen previousScreen;

	private String vin;
	private String customerUsername;

	public ListPaymentCustomerScreen(Screen previouScreen, String vin, String customerUsername) throws IOException {
		contractService = ServiceUtil.getContractService();
		this.previousScreen = previouScreen;

		this.vin = vin;
		this.customerUsername = customerUsername;
	}

	@Override
	public void display() {
		System.out.println("------------- YOUR PAYMENTS ---------------");

		System.out.println("Car with vin number: " + vin);

		System.out.println("-------------------------------------------");

		try {

			Iterator<Payment> paymentsIterator;
			paymentsIterator = contractService.getRemainingPaymentsForCar(vin, customerUsername).iterator();

			while (paymentsIterator.hasNext()) {
				Payment payment = paymentsIterator.next();
				System.out.println(payment);
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (NotFoundRecordException e) {
			System.out.println(e.getMessage());
		}

		int opt = 0;

		System.out.println("----------- MENU -----------");
		System.out.println("1. Go back to main menu.");
		System.out.println("----------------------------");

		do {
			System.out.println("Select an option from the menu:");
			opt = InputUtil.getNumber(1, 1);

		} while (opt < 1 || opt > 1);

		if (this.previousScreen != null) {
			previousScreen.display();
		}
	}

}
