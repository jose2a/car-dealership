package com.revature.cardealership.ui.screens;

import java.io.IOException;
import java.util.Iterator;

import com.revature.cardealership.exceptions.NotFoundRecordException;
import com.revature.cardealership.model.Car;
import com.revature.cardealership.services.CarService;
import com.revature.cardealership.utils.InputUtil;
import com.revature.cardealership.utils.ServiceUtil;

public class ListCarScreen implements Screen {

	private CarService carService;

	private Screen previousScreen;

	public ListCarScreen(Screen previousScreen) throws IOException {
		this.carService = ServiceUtil.getCarService();
		this.previousScreen = previousScreen;
	}

	@Override
	public void display() {
		System.out.println("--------- CARS ---------");

		Iterator<Car> carIterator = carService.getCars().iterator();

		while (carIterator.hasNext()) {
			Car car = carIterator.next();
			System.out.println(car.toSingleLineString());
		}

		int opt = 0;

		System.out.println("----- MENU -----");
		System.out.println("1. Remove a car.");
		System.out.println("2. Go back to main menu.");

		do {
			System.out.println("Select an option from the menu:");
			opt = InputUtil.getNumber(1, 2);

		} while (opt < 1 || opt > 2);

		switch (opt) {
		case 1:
			removeCar();
			break;
		case 2:
			break;
		}

		if (previousScreen != null) {
			previousScreen.display();
		}

	}

	private void removeCar() {

		try {
			InputUtil.getString();

			System.out.println("Enter VIN number of the car to be deleted:");
			String vin = InputUtil.getString();

			carService.removeCar(vin);

			System.out.println("Car removed successfully!!!");

		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (NotFoundRecordException e) {
			System.out.println(e.getMessage());
		}
	}

}
