package com.revature.cardealership.dao;

import com.revature.cardealership.model.Dealership;

public interface DealershipDAO {

	boolean loadDealership();

	void setDealership(Dealership dealership);
	
	Dealership getDealership();
	
	boolean save();
}
