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
		
		if(user instanceof Employee) {
			System.out.println("Employee");
		}
		if(user instanceof Customer) {
			System.out.println("Customer");
		}

	}

}
