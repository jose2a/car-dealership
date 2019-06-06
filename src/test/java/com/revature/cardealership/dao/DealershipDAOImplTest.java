package com.revature.cardealership.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.revature.cardealership.model.Dealership;

public class DealershipDAOImplTest {

	private static final String FILE_NAME = "dealership.dat";
	private DealershipDAO dealershipDAO;

	@Before
	public void setUp() throws Exception {
		dealershipDAO = new DealershipDAOImpl(FILE_NAME);
	}

	@Test
	public void init_File_Does_No_Exist_Returns_False() {		
		DealershipDAO result = dealershipDAO;
		
		assertFalse(result.loadDealership());
	}
	
	@Test
	public void init_File_Exist_Returns_True() {		
		DealershipDAO result = dealershipDAO;
		
		assertTrue(result.loadDealership());
	}
	
	@Test
	public void getDealership_Should_Return_Dealership() {
		dealershipDAO.loadDealership();
		
		Dealership dealership = dealershipDAO.getDealership();
		
		String expected = "ABC Autosales";
		
		assertEquals(expected, dealership.getName());
	}
	
	@Test
	public void save_File_Does_No_Exist_Returns_False() {		
		DealershipDAO result = dealershipDAO;
		
		assertFalse(result.save());
	}
	
	@Test
	public void save_File_Exist_Returns_True() {
		Dealership dealership = new Dealership("ABC Autosales");
		dealershipDAO.setDealership(dealership);
		
		DealershipDAO result = dealershipDAO;
		
		assertTrue(result.save());
	}

}
