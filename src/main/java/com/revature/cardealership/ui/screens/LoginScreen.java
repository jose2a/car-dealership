package com.revature.cardealership.ui.screens;

import java.io.IOException;

import com.revature.cardealership.model.User;
import com.revature.cardealership.services.UserService;
import com.revature.cardealership.ui.controller.MenuOptions;
import com.revature.cardealership.ui.listeners.ChooseOptionListener;
import com.revature.cardealership.ui.listeners.LoginUserListener;
import com.revature.cardealership.utils.InputUtil;
import com.revature.cardealership.utils.ServiceUtil;

public class LoginScreen implements Screen {

	private LoginUserListener loginUserListener;
	private ChooseOptionListener chooseOptionListener;

	private UserService userService;

	private String username;
	private String password;
	
	private Screen previousScreen;

	public LoginScreen() throws IOException {
		userService = ServiceUtil.getUserService();
	}

	public LoginScreen(Screen previousScreen) throws IOException {
		this();
		this.previousScreen = previousScreen;
	}
	
	@Override
	public void display() {
		User user = null;

		String opt = null;

		do {
			System.out.println("--------- LOGIN ----------\n");
			System.out.println("Enter username:");
			username = InputUtil.getString();
			System.out.println("Enter password:");
			password = InputUtil.getString();

			try {
				user = userService.login(username, password);
				
				if (user != null) {
//					fireLoginEvent(user);
					System.out.println("User authenticated!!!");
					break;
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

//	@Override
//	public void display() {
//		User user = null;
//
//		String opt = null;
//
//		do {
//			System.out.println("--------- LOGIN ----------\n");
//			System.out.println("Enter username:");
//			username = InputUtil.getString();
//			System.out.println("Enter password:");
//			password = InputUtil.getString();
//
//			try {
//				user = userService.login(username, password);
//				
//				if (user != null) {
//					fireLoginEvent(user);
//					break;
//				}
//			} catch (IllegalArgumentException ex) {
//				System.out.println(ex.getMessage());
//			}
//
//			System.out.println("Do you want to continue? y/n");
//			opt = InputUtil.getString();
//
//		} while (user == null && (opt.equals(null) || opt.toLowerCase().equals("y")));
//
//		if (opt != null) {
//			if (!opt.toLowerCase().equals("y")) {
//				fireChooseEvent(MenuOptions.EXIT);
//			}
//		}
//
//	}

	public void setLoginUserListener(LoginUserListener loginUserListener) {
		this.loginUserListener = loginUserListener;
	}

	private void fireLoginEvent(User user) {
		if (this.loginUserListener != null) {
			loginUserListener.userLogin(user);
		}
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
