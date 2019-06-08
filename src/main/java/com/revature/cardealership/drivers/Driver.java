package com.revature.cardealership.drivers;

import com.revature.cardealership.ui.screens.Screen;
import com.revature.cardealership.ui.screens.WelcomeScreen;

public class Driver {

	public static void main(String[] args) {
//		Controller controller = new Controller();
		
		Screen welcomeScreen = new WelcomeScreen(null);
		welcomeScreen.display();
//		controller.displayLogin();
//
//		User user = controller.getUser();
//		
//		if (user != null) {
//			
//			if (user instanceof Employee) {
//				controller.displayEmployeeMenu();
//				MenuOptions option = null;
//				boolean cont = true;
//
//				do {
//					option = controller.getOption();
//
//					switch (option) {
//					case LIST_CARS_EMPLOYEE:
//						controller.displayListCar();
//						break;
//					case EXIT:
//						cont = false;
//
//					default:
//						break;
//					}
//					controller.getLastScreenOpenDeque().pop().display();
//				} while (cont);
//			}
//			if (user instanceof Customer) {
//				System.out.println("Customer");
//			}
//		}


	}

}
