package com.revature.cardealership.utils;

import com.revature.cardealership.dao.DealershipDAO;
import com.revature.cardealership.dao.DealershipDAOImpl;

public class DAOUtils {
	
	private static String FILE_NAME = "dealership.dat";

	public static DealershipDAO geDealershipDAO() {
		return new DealershipDAOImpl(FILE_NAME);
	}
	
}
