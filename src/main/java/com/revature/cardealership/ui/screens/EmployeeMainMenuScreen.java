package com.revature.cardealership.ui.screens;

import com.revature.cardealership.ui.controller.MenuOptions;
import com.revature.cardealership.ui.listeners.ChooseOptionListener;
import com.revature.cardealership.utils.InputUtil;

public class EmployeeMainMenuScreen implements Screen {

	private ChooseOptionListener chooseOptionListener;

	@Override
	public void display() {
		int opt = 0;
		System.out.println("----- EMPLOYEE MENU -----");
		System.out.println("1. See cars.");
		System.out.println("2. Add car.");
		System.out.println("3. See offers.");
		System.out.println("4. See payments.");
		System.out.println("5. Exit.");

		do {
			System.out.println("Choose an option from menu:");
			opt = InputUtil.getNumber(1, 5);

		} while (opt < 1 || opt > 4);

		MenuOptions option = null;

		switch (opt) {
		case 1:
			option = MenuOptions.LIST_CARS_EMPLOYEE;
			break;
		case 2:
			option = MenuOptions.ADD_CAR;
			break;
		case 3:
			option = MenuOptions.LIST_OFFER_EMPLOYEE;
			break;
		case 4:
			option = MenuOptions.LIST_PAYMENTS_EMPLOYEE;
			break;
		case 5:
			option = MenuOptions.EXIT;
		default:
			break;
		}
		
		fireChooseEvent(option);
	}

	public void setChooseOptionListener(ChooseOptionListener chooseOptionListener) {
		this.chooseOptionListener = chooseOptionListener;
	}

	private void fireChooseEvent(MenuOptions option) {
		if (this.chooseOptionListener != null) {
			this.chooseOptionListener.optionChosen(option);
		}
	}

}
