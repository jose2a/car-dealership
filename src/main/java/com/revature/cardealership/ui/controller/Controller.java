package com.revature.cardealership.ui.controller;

import com.revature.cardealership.model.User;
import com.revature.cardealership.ui.listeners.ChooseOptionListener;
import com.revature.cardealership.ui.listeners.LoginUserListener;
import com.revature.cardealership.ui.screens.EmployeeMainMenuScreen;
import com.revature.cardealership.ui.screens.ListCarScreen;
import com.revature.cardealership.ui.screens.LoginScreen;
import com.revature.cardealership.ui.screens.Screen;
import com.revature.cardealership.utils.LoggingUtil;

public class Controller implements LoginUserListener, ChooseOptionListener {

	private Screen lastScreenOpen; // Save a reference to the last screen showed so that we can go back

	private LoginScreen loginScreen;
	private EmployeeMainMenuScreen employeeMainMenuScreen;
	private ListCarScreen listCarScreen;

	private User user;
	private int option;

	public Controller() {
		setUp();
	}

	private void setUp() {
		try {
			loginScreen = new LoginScreen();
			employeeMainMenuScreen = new EmployeeMainMenuScreen();
			listCarScreen = new ListCarScreen();
		} catch (Exception e) {
			LoggingUtil.error(e.getMessage());
		}

	}

	public void displayLogin() {
		loginScreen.setLoginUserListener(this);
		loginScreen.display();
	}

	public void displayEmployeeMenu() {
		employeeMainMenuScreen.setChooseOptionListener(this);
		employeeMainMenuScreen.display();
	}

	public void displayListCar() {
		this.lastScreenOpen = employeeMainMenuScreen;
		listCarScreen.display();
	}

	@Override
	public void userLogin(User user) {
		this.user = user;
	}

	@Override
	public void optionChosen(int option) {
		this.option = option;
	}

	public Screen getLastScreenOpen() {
		return lastScreenOpen;
	}

	public void setLastScreenOpen(Screen lastScreenOpen) {
		this.lastScreenOpen = lastScreenOpen;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getOption() {
		return option;
	}

	public void setOption(int option) {
		this.option = option;
	}

}
