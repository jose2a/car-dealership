package com.revature.cardealership.services;

import java.io.IOException;

import com.revature.cardealership.dao.DealershipDAO;
import com.revature.cardealership.model.Dealership;
import com.revature.cardealership.model.User;
import com.revature.cardealership.utils.DAOUtils;

public class UserServiceImpl implements UserService {
	
	private DealershipDAO dao = DAOUtils.geDealershipDAO();
	private Dealership dealership;
	
	public UserServiceImpl() throws IOException {
		dao.loadDealership();
		this.dealership = dao.getDealership();
	}

	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean register(String username, String password, String firstName, String lastName) {
		// TODO Auto-generated method stub
		return false;
	}

}
