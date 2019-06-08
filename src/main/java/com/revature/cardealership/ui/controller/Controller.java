package com.revature.cardealership.ui.controller;

import com.revature.cardealership.model.User;
import com.revature.cardealership.ui.LoginScreen;
import com.revature.cardealership.ui.listeners.LoginUserListener;

public class Controller implements LoginUserListener {

	private LoginScreen loginScreen;
	private User user;

	public Controller() {
		setUp();
	}

	private void setUp() {
		try {
			loginScreen = new LoginScreen();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void displayLogin() {
		loginScreen.setLoginUserListener(this);
		loginScreen.display();
	}

	@Override
	public void userLoggedIn(User user) {
		this.user = user;

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
