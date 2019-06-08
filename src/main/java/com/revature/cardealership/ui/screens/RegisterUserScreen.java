package com.revature.cardealership.ui.screens;

import java.io.IOException;

import com.revature.cardealership.exceptions.PreexistingRecordException;
import com.revature.cardealership.services.UserService;
import com.revature.cardealership.utils.InputUtil;
import com.revature.cardealership.utils.LogUtil;
import com.revature.cardealership.utils.ServiceUtil;

public class RegisterUserScreen implements Screen {

	private UserService userService;

	private Screen previousScreen;

	public RegisterUserScreen(Screen previousScreen) throws IOException {
		userService = ServiceUtil.getUserService();
		this.previousScreen = previousScreen;
	}

	@Override
	public void display() {
		boolean isRegistered = false;

		String opt = null;

		do {
			System.out.println("--------- REGISTER CUSTOMER ----------");

			InputUtil.getString();

			System.out.println("Enter username:");
			String username = InputUtil.getString();

			System.out.println("Enter password:");
			String password = InputUtil.getString();

			System.out.println("Enter first name:");
			String firstName = InputUtil.getString();

			System.out.println("Enter last name:");
			String lastName = InputUtil.getString();
			
			System.out.println("--------------------------------------");

			try {
				isRegistered = userService.registerCustomer(username, password, firstName, lastName);

				if (isRegistered) {
					LogUtil.trace("Customer registered successfully.");
					System.out.println("Customer registered successfully.");
				}
			} catch (IllegalArgumentException ex) {
				System.out.println(ex.getMessage());
			} catch (PreexistingRecordException e) {
				System.out.println(e.getMessage());
			}

			System.out.println("Do you want to continue? y/n");
			opt = InputUtil.getString();

		} while (!isRegistered && (opt.equals(null) || opt.toLowerCase().equals("y")));

		if (opt != null) {
			if (!opt.toLowerCase().equals("y")) {
				if (previousScreen != null) {
					previousScreen.display();
				}
			}
		}

	}

}
