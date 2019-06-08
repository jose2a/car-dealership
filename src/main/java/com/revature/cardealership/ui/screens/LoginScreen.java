package com.revature.cardealership.ui.screens;

import java.io.IOException;

import com.revature.cardealership.model.Customer;
import com.revature.cardealership.model.Employee;
import com.revature.cardealership.model.User;
import com.revature.cardealership.services.UserService;
import com.revature.cardealership.utils.InputUtil;
import com.revature.cardealership.utils.LogUtil;
import com.revature.cardealership.utils.ServiceUtil;

public class LoginScreen implements Screen {

	private UserService userService;
	
	private Screen previousScreen;

	public LoginScreen(Screen previousScreen) throws IOException {
		userService = ServiceUtil.getUserService();
		this.previousScreen = previousScreen;
	}
	
	@Override
	public void display() {
		User user = null;

		String opt = null;

		do {
			System.out.println("--------- LOGIN ----------");
			
			InputUtil.getString();
			
			System.out.println("Enter username:");
			String username = InputUtil.getString();
			
			System.out.println("Enter password:");
			String password = InputUtil.getString();
			
			System.out.println("--------------------------");

			try {
				user = userService.login(username, password);
				
				if (user != null) {
					LogUtil.trace("User authenticated.");
					userLoggedIn(user);
				}
			} catch (IllegalArgumentException ex) {
				System.out.println(ex.getMessage());
			}

			System.out.println("Do you want to continue? y/n");
			opt = InputUtil.getString();

		} while (user == null && (opt.equals(null) || opt.toLowerCase().equals("y")));

		if (opt != null) {
			if (!opt.toLowerCase().equals("y")) {
				if (previousScreen != null) {
					previousScreen.display();
				}
			}
		}

	}
	
	private void userLoggedIn(User user) {
		System.out.println("Welcome: " + user);
		
		if (user instanceof Employee) {
			new EmployeeMenuScreen().display();
		} else if (user instanceof Customer) {
			new CustomerMenuScreen(user.getUsername()).display();
		}
	}
}
