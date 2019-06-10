package com.revature.cardealership.ui.screens;

import java.io.IOException;
import java.util.Iterator;

import com.revature.cardealership.model.Car;
import com.revature.cardealership.services.CarService;
import com.revature.cardealership.utils.InputUtil;
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
		System.out.println("----------------- YOUR CARS -------------------");

		try {
			Iterator<Car> carIterator = carService.getCarsByCustomerUsername(customerUsername).iterator();

			while (carIterator.hasNext()) {
				Car car = carIterator.next();
				System.out.println(car.toSingleLineString());
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			
			if (previousScreen != null) {
				previousScreen.display();
			}
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
			System.out.println("------------------------------------------------------------------------");
			
			InputUtil.getString();

			System.out.println("Enter the vin number of the car that you want to see the remaining payments:");
			String vin = InputUtil.getString();
			try {
				Screen listPaymentCustomer = new ListPaymentCustomerScreen(this, vin, customerUsername);
				listPaymentCustomer.display();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 2:
			break;
		}

		if (previousScreen != null) {
			previousScreen.display();
		}

	}

}
