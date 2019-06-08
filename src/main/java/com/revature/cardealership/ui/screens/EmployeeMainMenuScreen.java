package com.revature.cardealership.ui.screens;

import com.revature.cardealership.ui.listeners.ChooseOptionListener;
import com.revature.cardealership.utils.InputUtil;

public class EmployeeMainMenuScreen implements Screen {
	
	private ChooseOptionListener chooseOptionListener;

	@Override
	public void display() {
		int option = 0;
		System.out.println("----- EMPLOYEE MENU -----");
		System.out.println("1. See cars.");
		System.out.println("2. Add car.");
		System.out.println("3. See offers.");
		System.out.println("4. See payments.");
		System.out.println("5. Exit.");

		do {
			System.out.println("Choose an option from menu:");
			option = InputUtil.getNumber(1, 5);
			
		} while (option < 1 || option > 4);

		fireChooseEvent(option);
	}

	public void setChooseOptionListener(ChooseOptionListener chooseOptionListener) {
		this.chooseOptionListener = chooseOptionListener;
	}
	
	private void fireChooseEvent(int option) {
		if (this.chooseOptionListener != null) {
			this.chooseOptionListener.optionChosen(option);
		}
	}

}
