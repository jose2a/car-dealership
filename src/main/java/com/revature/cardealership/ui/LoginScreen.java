package com.revature.cardealership.ui;

import java.io.IOException;

import com.revature.cardealership.model.User;
import com.revature.cardealership.services.UserService;
import com.revature.cardealership.ui.listeners.LoginUserListener;
import com.revature.cardealership.utils.InputUtil;
import com.revature.cardealership.utils.ServiceUtil;

public class LoginScreen implements Screen {

	private LoginUserListener loginUserListener;
	private UserService userService;

	private String username;
	private String password;

	public LoginScreen() throws IOException {
		userService = ServiceUtil.getUserService();
	}

	@Override
	public void display() {
		User user = null;
		
		do {
			System.out.println("LOGIN");
			System.out.println("Enter username:");
			username = InputUtil.getString();
			System.out.println("Enter password:");
			password = InputUtil.getString();
			
			user = userService.login(username, password);
			
		} while (user == null);
		
		fireLoginEvent(user);
	}

	public void setLoginUserListener(LoginUserListener loginUserListener) {
		this.loginUserListener = loginUserListener;
	}
	
	private void fireLoginEvent(User user) {
		if (this.loginUserListener != null) {
			loginUserListener.userLogin(user);
		}
	}

}
