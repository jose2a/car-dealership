package com.revature.cardealership.ui.screens;

import java.io.IOException;

import com.revature.cardealership.utils.InputUtil;
import com.revature.cardealership.utils.LogUtil;

public class WelcomeScreen implements Screen {
	
	private Screen previousScreen;
	
	public WelcomeScreen(Screen previousScreen) {
		this.previousScreen = previousScreen;
	}

	@Override
	public void display() {
		System.out.println("---------- WELCOME TO MY DEALERSHIP ---------");
		
		int opt = 0;
		
		do {
			System.out.println("1. Login.");
			System.out.println("2. Register.");
			System.out.println("3. Exit.");
			
			System.out.println("Selec an option from the menu:");
			
			opt = InputUtil.getNumber(1, 3);
		} while (opt < 1 && opt > 3);
		
		try {
			optionSelected(opt);
		} catch (IOException e) {
			LogUtil.error(e.getMessage());
		}

	}
	
	private void optionSelected(int opt) throws IOException {
		switch (opt) {
		case 1:
			LoginScreen loginScreen = new LoginScreen(this);
			loginScreen.display();
			break;
		case 2:
			// Register user
			break;
		case 3:
			if (previousScreen != null) {
				previousScreen.display();
			}

		default:
			break;
		}
	}

}
