package com.revature.cardealership.drivers;

import com.revature.cardealership.model.Customer;
import com.revature.cardealership.model.Employee;
import com.revature.cardealership.model.User;
import com.revature.cardealership.ui.controller.Controller;

public class Driver {

	public static void main(String[] args) {
		Controller controller = new Controller();
		controller.displayLogin();
		
		User user = controller.getUser();
		System.out.println(user);
		
		int option = 0;
		
		if(user instanceof Employee) {
			controller.displayEmployeeMenu();
			option = controller.getOption();
			
			switch (option) {
			case 1:
				controller.displayListCar();
				try {
					Thread.sleep(20000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			default:
				break;
			}
			
			controller.getLastScreenOpen().display();
		}
		if(user instanceof Customer) {
			System.out.println("Customer");
		}

	}

}
