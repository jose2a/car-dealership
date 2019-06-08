package com.revature.cardealership.utils;

import com.revature.cardealership.dao.DealershipDAO;
import com.revature.cardealership.dao.DealershipDAOImpl;

public class DAOUtils {
	
	private static String FILE_NAME = "dealership.dat";
	private static final String DEALERSHIP_NAME = "ABC Autosales";

	public static DealershipDAO geDealershipDAO() {
		return new DealershipDAOImpl(FILE_NAME, DEALERSHIP_NAME);
	}
	
}
