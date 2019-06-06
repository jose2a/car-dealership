package com.revature.cardealership.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DealershipDAOImplTest {

	private static final String FILE_NAME = "dealership.dat";
	private DealershipDAO dealershipDAO;

	@Before
	public void setUp() throws Exception {
		dealershipDAO = new DealershipDAOImpl(FILE_NAME);
	}

	@Test
	public void init_File_Does_No_Exist_Returns_False() {		
		assertFalse(dealershipDAO.loadDealership());
	}
	
	@Test
	public void init_File_Exist_Returns_True() {		
		assertTrue(dealershipDAO.loadDealership());
	}

	@Test
	public void testInsertDealership() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateDealership() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteDealership() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDealership() {
		fail("Not yet implemented");
	}
	
	@Test
	public void save_File_Does_No_Exist_Returns_False() {		
		assertFalse(dealershipDAO.loadDealership());
	}
	
	@Test
	public void save_File_Exist_Returns_True() {		
		assertTrue(dealershipDAO.loadDealership());
	}

}
