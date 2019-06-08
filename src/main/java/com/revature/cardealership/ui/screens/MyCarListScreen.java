package com.revature.cardealership.ui.screens;

import java.io.IOException;
import java.util.Iterator;

import com.revature.cardealership.model.Car;
import com.revature.cardealership.services.CarService;
import com.revature.cardealership.services.ContractService;
import com.revature.cardealership.utils.InputUtil;
import com.revature.cardealership.utils.LogUtil;
import com.revature.cardealership.utils.ServiceUtil;

public class MyCarListScreen implements Screen {

	private CarService carService;
	private String customerUsername;

	private Screen previousScreen;

	public MyCarListScreen(Screen previousScreen, String customerUsername) throws IOException {
		this.carService = ServiceUtil.getCarService();
		this.customerUsername = customerUsername;
		this.previousScreen = previousScreen;
	}

	@Override
	public void display() {
		System.out.println("--------- YOUR CARS ---------");

		Iterator<Car> carIterator = carService.getCarsByCustomerUsername(customerUsername).iterator();

		while (carIterator.hasNext()) {
			Car car = carIterator.next();
			System.out.println(car.toSingleLineString());
		}

		int opt = 0;

		System.out.println("----------------- MENU -----------------");
		System.out.println("1. View remaining payments for a car.");
		System.out.println("2. Go back to main menu.");
		System.out.println("----------------------------------------");

		do {
			System.out.println("Select an option from the menu:");
			opt = InputUtil.getNumber(1, 2);

		} while (opt < 1 || opt > 2);

		switch (opt) {
		case 1:
			showRemainingPaymentsForACar();
			break;
		case 2:
			break;
		}

		if (previousScreen != null) {
			previousScreen.display();
		}

	}
	
	private void showRemainingPaymentsForACar() {
		if (carService != null) {
			carService = null;
		}
		

		try {
			ContractService contractService = ServiceUtil.getContractService();
			
			InputUtil.getString();

			System.out.println("Enter the contract No. number of the car that you want to see the remaining payments:");
			String contractId = InputUtil.getString();

			contractService.getRemainingPayments(contractId);

			System.out.println("The offer was successfully made!!!");
			System.out.println("The car will be in your list if your offer gets accepted.");

		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			LogUtil.error(e.getMessage());
		}
	}

}
