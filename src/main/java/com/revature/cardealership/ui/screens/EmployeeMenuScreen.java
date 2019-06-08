package com.revature.cardealership.ui.screens;

import java.io.IOException;

import com.revature.cardealership.utils.InputUtil;
import com.revature.cardealership.utils.LogUtil;

public class EmployeeMenuScreen implements Screen {

	public EmployeeMenuScreen() {
	}

	@Override
	public void display() {
		int opt = 0;
		
		System.out.println("----- EMPLOYEE MENU -----");
		System.out.println("1. See cars.");
		System.out.println("2. Add car.");
		System.out.println("3. See offers.");
		System.out.println("4. See payments.");
		System.out.println("5. Exit.");
		System.out.println("-------------------------");

		do {
			System.out.println("Select an option from the menu:");
			opt = InputUtil.getNumber(1, 5);

		} while (opt < 1 || opt > 5);

		switch (opt) {
		case 1:
			try {
				Screen listCarScreen = new ListCarScreen(this);
				listCarScreen.display();
			} catch (IOException e) {
				LogUtil.error(e.getMessage());
			}
			break;
		case 2:
			try {
				Screen addCarScreen = new AddCarScreen(this);
				addCarScreen.display();
			} catch (IOException e) {
				LogUtil.error(e.getMessage());
			}
			break;
		case 3:
			try {
				Screen listOfferScreen = new ListOfferScreen(this);
				listOfferScreen.display();
			} catch (IOException e) {
				LogUtil.error(e.getMessage());
			}
			break;
		case 4:
			try {
				Screen listPaymentScreen = new ListPaymentScreen(this);
				listPaymentScreen.display();
			} catch (IOException e) {
				LogUtil.error(e.getMessage());
			}
			break;
		case 5:
			System.exit(0);
		default:
			break;
		}
		
	}

}
