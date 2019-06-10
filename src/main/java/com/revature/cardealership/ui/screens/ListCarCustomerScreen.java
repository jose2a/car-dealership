package com.revature.cardealership.ui.screens;

import java.io.IOException;
import java.util.Iterator;

import com.revature.cardealership.exceptions.NotFoundRecordException;
import com.revature.cardealership.model.Car;
import com.revature.cardealership.services.CarService;
import com.revature.cardealership.services.ContractService;
import com.revature.cardealership.utils.InputUtil;
import com.revature.cardealership.utils.LogUtil;
import com.revature.cardealership.utils.ServiceUtil;

public class ListCarCustomerScreen implements Screen {

	private CarService carService;
	private String customerUsername;

	private Screen previousScreen;

	public ListCarCustomerScreen(Screen previousScreen, String customerUsername) throws IOException {
		this.carService = ServiceUtil.getCarService();
		this.customerUsername = customerUsername;
		this.previousScreen = previousScreen;
	}

	@Override
	public void display() {
		System.out.println("------------ CARS ------------");

		Iterator<Car> carIterator = carService.getCars().iterator();

		while (carIterator.hasNext()) {
			Car car = carIterator.next();
			System.out.println(car.toSingleLineString());
		}

		int opt = 0;

		System.out.println("------------ MENU -------------");
		System.out.println("1. Make an offer for a car.");
		System.out.println("2. Go back to main menu.");
		System.out.println("------------------------------");

		do {
			System.out.println("Select an option from the menu:");
			opt = InputUtil.getNumber(1, 2);

		} while (opt < 1 || opt > 2);

		switch (opt) {
		case 1:
			makeAnOffer();
			break;
		case 2:
			break;
		}

		if (previousScreen != null) {
			previousScreen.display();
		}

	}
	
	private void makeAnOffer() {
		if (carService != null) {
			carService = null;
		}

		try {
			System.out.println("-------------------- MAKE AN OFFER ------------------------");
			ContractService contractService = ServiceUtil.getContractService();
			
			InputUtil.getString();

			System.out.println("Enter VIN number of the car that you want a make an offer:");
			String vin = InputUtil.getString();
			
			System.out.println("Enter the amount of your offer:");
			double amount = InputUtil.getDouble();

			contractService.makeAnOffer(customerUsername, vin, amount);
			
			System.out.println("-----------------------------------------------------------");

			System.out.println("The offer was successfully made!!!");
			System.out.println("The car will be in your list if your offer gets accepted.");

		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (NotFoundRecordException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			LogUtil.error(e.getMessage());
		}
	}

}
