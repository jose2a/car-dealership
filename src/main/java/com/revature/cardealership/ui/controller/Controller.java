package com.revature.cardealership.ui.controller;

import java.util.Deque;
import java.util.LinkedList;

import com.revature.cardealership.model.User;
import com.revature.cardealership.ui.listeners.ChooseOptionListener;
import com.revature.cardealership.ui.listeners.LoginUserListener;
import com.revature.cardealership.ui.screens.EmployeeMenuScreen;
import com.revature.cardealership.ui.screens.ListCarScreen;
import com.revature.cardealership.ui.screens.LoginScreen;
import com.revature.cardealership.ui.screens.Screen;
import com.revature.cardealership.utils.LogUtil;

public class Controller implements LoginUserListener, ChooseOptionListener {

	private Deque<Screen> lastScreenOpenDeque; // Save a reference to the last screen showed so that we can go back

	private LoginScreen loginScreen;
	private EmployeeMenuScreen employeeMainMenuScreen;
	private ListCarScreen listCarScreen;

	private User user;
	private MenuOptions option;

	public Controller() {
		setUp();
		lastScreenOpenDeque = new LinkedList<>();
	}

	private void setUp() {
		try {
			loginScreen = new LoginScreen();
//			employeeMainMenuScreen = new EmployeeMenuScreen();
//			listCarScreen = new ListCarScreen();
		} catch (Exception e) {
			LogUtil.error(e.getMessage());
		}

	}

	public void displayLogin() {
//		loginScreen.setLoginUserListener(this);
//		loginScreen.setChooseOptionListener(this);
		
		loginScreen.display();

		lastScreenOpenDeque.push(loginScreen);
	}

	public void displayEmployeeMenu() {
//		employeeMainMenuScreen.setChooseOptionListener(this);
		
		employeeMainMenuScreen.display();
	}

	public void displayListCar() {
		lastScreenOpenDeque.push(employeeMainMenuScreen);
		
		listCarScreen.display();
	}

	@Override
	public void userLogin(User user) {
		this.user = user;
	}

	@Override
	public void optionChosen(MenuOptions option) {
		this.option = option;
	}

	public Deque<Screen> getLastScreenOpenDeque() {
		return lastScreenOpenDeque;
	}

	public void setLastScreenOpenDeque(Deque<Screen> lastScreenOpenDeque) {
		this.lastScreenOpenDeque = lastScreenOpenDeque;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public MenuOptions getOption() {
		return option;
	}

}
