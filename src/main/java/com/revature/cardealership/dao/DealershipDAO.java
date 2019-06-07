package com.revature.cardealership.dao;

import java.io.IOException;

import com.revature.cardealership.model.Dealership;

public interface DealershipDAO {

	boolean loadDealership() throws IOException;

	void setDealership(Dealership dealership);
	
	Dealership getDealership();
	
	boolean save();
}
