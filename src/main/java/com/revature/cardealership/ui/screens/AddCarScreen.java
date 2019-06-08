package com.revature.cardealership.ui.screens;

import java.io.IOException;

import com.revature.cardealership.exceptions.PreexistingRecordException;
import com.revature.cardealership.model.Car;
import com.revature.cardealership.services.CarService;
import com.revature.cardealership.utils.InputUtil;
import com.revature.cardealership.utils.ServiceUtil;

public class AddCarScreen implements Screen {

	private CarService carService;

	private Screen previousScreen;

	public AddCarScreen(Screen previousScreen) throws IOException {
		this.carService = ServiceUtil.getCarService();
		this.previousScreen = previousScreen;
	}

	@Override
	public void display() {

		System.out.println("--------- ADD CAR ---------");

		try {
			carService.addCar(getCarFromEmployee());

		} catch (PreexistingRecordException e) {
			System.out.println(e.getMessage());
		}

		if (previousScreen != null) {
			previousScreen.display();
		}

	}

	private Car getCarFromEmployee() {
		String vin; // VIN number
		String make; // Make
		String model; // Model
		double price; // Price
		boolean isSold = false; // Is the car sold?

		InputUtil.getString();

		System.out.println("Enter VIN number:");
		vin = InputUtil.getString();

		System.out.println("Enter make:");
		make = InputUtil.getString();

		System.out.println("Enter model:");
		model = InputUtil.getString();

		System.out.println("Enter price:");
		price = InputUtil.getDouble();

		Car car = new Car(vin, make, model, price, isSold);

		return car;
	}

}
