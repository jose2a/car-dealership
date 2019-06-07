package com.revature.cardealership.services;

import com.revature.cardealership.exceptions.PreexistingRecordException;
import com.revature.cardealership.model.User;

public interface UserService {

	User login(String username, String password);

	boolean registerCustomer(String username, String password, String firstName, String lastName)
			throws PreexistingRecordException;

}
