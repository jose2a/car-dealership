package com.revature.cardealership.services;

import com.revature.cardealership.model.User;

public interface UserService {
	
	public User login(String username, String password);
	public boolean register(String username, String password, String firstName, String lastName);

}
