package com.revature.cardealership.ui.screens;

import java.io.IOException;
import java.util.Iterator;

import com.revature.cardealership.model.Payment;
import com.revature.cardealership.services.ContractService;
import com.revature.cardealership.utils.InputUtil;
import com.revature.cardealership.utils.ServiceUtil;

public class ListPaymentScreen implements Screen {
	
	private ContractService contractService;

	private Screen previousScreen;

	public ListPaymentScreen(Screen previouScreen) throws IOException {
		contractService = ServiceUtil.getContractService();
		this.previousScreen = previouScreen;
	}

	@Override
	public void display() {
		System.out.println("-------------- PAYMENTS --------------");

		Iterator<Payment> paymentsIterator = contractService.getAllPayments().iterator();

		while (paymentsIterator.hasNext()) {
			Payment payment = paymentsIterator.next();
			System.out.println(payment);
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
