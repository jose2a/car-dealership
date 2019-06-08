package com.revature.cardealership.utils;

import java.io.IOException;

import com.revature.cardealership.services.UserService;
import com.revature.cardealership.services.UserServiceImpl;

public class ServiceUtil {
	
	public static UserService getUserService() throws IOException {
		return new UserServiceImpl();
	}

}
