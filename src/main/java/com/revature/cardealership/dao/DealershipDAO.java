package com.revature.cardealership.dao;

import com.revature.cardealership.model.Dealership;

public interface DealershipDAO {

	boolean loadDealership();
	void insertDealership(Dealership dealership);
	void updateDealership(Dealership dealership);
	void deleteDealership(Dealership dealership);
	
	public Dealership getDealership();
	
	public boolean save();
}
