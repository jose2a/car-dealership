package com.revature.cardealership.ui.screens;

import java.io.IOException;

import com.revature.cardealership.utils.InputUtil;
import com.revature.cardealership.utils.LogUtil;

public class CustomerMenuScreen implements Screen {

	private String customerUsername;

	public CustomerMenuScreen(String customerUsername) {
		this.customerUsername = customerUsername;
	}

	@Override
	public void display() {
		int opt = 0;

		System.out.println("------- CUSTOMER MENU -------");
		System.out.println("1. See cars.");
		System.out.println("2. See the cars you own.");
		System.out.println("3. Exit.");
		System.out.println("-----------------------------");

		do {
			System.out.println("Select an option from the menu:");
			opt = InputUtil.getNumber(1, 3);

		} while (opt < 1 || opt > 3);

		switch (opt) {
		case 1:
			try {
				Screen listCarScreen = new ListCarCustomerScreen(this, this.customerUsername);
				listCarScreen.display();
			} catch (IOException e) {
				LogUtil.error(e.getMessage());
			}
			break;
		case 2:
			try {
				Screen myCarListScreen = new MyCarListScreen(this, customerUsername);
				myCarListScreen.display();
			} catch (IOException e) {
				LogUtil.error(e.getMessage());
			}
			break;
		case 3:
			System.exit(0);
			break;
		default:
			break;
		}

	}

}
