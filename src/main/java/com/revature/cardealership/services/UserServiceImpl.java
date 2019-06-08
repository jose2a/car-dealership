package com.revature.cardealership.services;

import java.io.IOException;
import java.util.Iterator;

import com.revature.cardealership.dao.DealershipDAO;
import com.revature.cardealership.exceptions.PreexistingRecordException;
import com.revature.cardealership.model.Customer;
import com.revature.cardealership.model.Dealership;
import com.revature.cardealership.model.User;
import com.revature.cardealership.utils.DAOUtil;

public class UserServiceImpl implements UserService {

	private DealershipDAO dao = DAOUtil.geDealershipDAO();
	private Dealership dealership;

	public UserServiceImpl() throws IOException {
		dao.loadDealership();
		this.dealership = dao.getDealership();
	}

	@Override
	public User login(String username, String password) {
		if (username == null || password == null) {
			throw new IllegalArgumentException("Username and password should not be empty.");
		}

		Iterator<User> users = this.dealership.getUsers().iterator();

		User user = null;

		while (users.hasNext()) {
			user = users.next();

			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				return user;
			}

		}

		return null;
	}

	@Override
	public boolean registerCustomer(String username, String password, String firstName, String lastName)
			throws PreexistingRecordException {

		validateCustomer(username, password, firstName, lastName);

		Iterator<User> users = this.dealership.getUsers().iterator();

		User user = null;

		while (users.hasNext()) {
			user = users.next();

			if (user.getUsername().equals(username)) {
				throw new PreexistingRecordException("User with the same username is already registered.");
			}
		}

		Customer theCustomer = new Customer(username, password, firstName, lastName);

		this.dealership.addUser(theCustomer);

		return dao.save();
	}

	private void validateCustomer(String username, String password, String firstName, String lastName) {
		if (username == null) {
			throw new IllegalArgumentException("Username shoud not be empty");
		}

		if (password == null) {
			throw new IllegalArgumentException("Password shoud not be empty");
		}

		if (firstName == null) {
			throw new IllegalArgumentException("Fist Name shoud not be empty");
		}

		if (lastName == null) {
			throw new IllegalArgumentException("Last Name shoud not be empty");
		}
	}

}
