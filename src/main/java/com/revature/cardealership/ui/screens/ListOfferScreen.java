package com.revature.cardealership.ui.screens;

import java.io.IOException;
import java.util.Iterator;

import com.revature.cardealership.exceptions.NotFoundRecordException;
import com.revature.cardealership.model.Contract;
import com.revature.cardealership.services.ContractService;
import com.revature.cardealership.utils.InputUtil;
import com.revature.cardealership.utils.ServiceUtil;

public class ListOfferScreen implements Screen {

	private ContractService contractService;

	private Screen previousScreen;

	public ListOfferScreen(Screen previouScreen) throws IOException {
		contractService = ServiceUtil.getContractService();
		this.previousScreen = previouScreen;
	}

	@Override
	public void display() {
		System.out.println("------------- OFFERS --------------");

		Iterator<Contract> contractIterator = contractService.getAllOffers().iterator();

		while (contractIterator.hasNext()) {
			Contract contract = contractIterator.next();
			System.out.println(contract.toSingleLineString());
		}

		int opt = 0;
		System.out.println("----------- MENU -----------");
		System.out.println("1. Accept an offer.");
		System.out.println("2. Reject an offer.");
		System.out.println("3. Go back to main menu.");
		System.out.println("----------------------------");

		do {
			System.out.println("Select an option from the menu:");
			opt = InputUtil.getNumber(1, 3);

		} while (opt < 1 || opt > 3);

		switch (opt) {
		case 1:
			acceptOffer();
			break;
		case 2:
			rejectOffer();
			break;
		case 3:
			break;
		}

		if (this.previousScreen != null) {
			previousScreen.display();
		}

	}

	private void acceptOffer() {

		try {
			System.out.println("--------------------------------------");
			
			InputUtil.getString();

			System.out.println("Enter offer number to be accepted:");
			String contractId = InputUtil.getString();

			contractService.acceptOffer(contractId);
			
			System.out.println("--------------------------------------");

			System.out.println("Offer accepted successfully!!!");
			
			System.out.println("--------------------------------------");

		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (NotFoundRecordException e) {
			System.out.println(e.getMessage());
		}
	}

	private void rejectOffer() {

		try {
			System.out.println("--------------------------------------");
			
			InputUtil.getString();

			System.out.println("Enter offer number to be rejected:");
			String contractId = InputUtil.getString();

			contractService.rejectOffer(contractId);
			
			System.out.println("--------------------------------------");

			System.out.println("Offer rejected successfully!!!");
			
			System.out.println("--------------------------------------");

		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (NotFoundRecordException e) {
			System.out.println(e.getMessage());
		}
	}

}
