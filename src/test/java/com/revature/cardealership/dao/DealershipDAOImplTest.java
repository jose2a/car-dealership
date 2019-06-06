package com.revature.cardealership.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.revature.cardealership.model.Car;
import com.revature.cardealership.model.Dealership;
import com.revature.cardealership.model.User;

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
	
	@Test
	public void save_Cart_To_File_Should_Return_True() {
		dealershipDAO.loadDealership();
		
		Dealership dealership = dealershipDAO.getDealership();
		dealership.addCar(new Car("1111111", "Toyota", "Corolla", 17000, false));
		dealership.addUser(new User("user1", "s3cret", "John", "Doe"));
		
		boolean result = dealershipDAO.save();
		
		assertTrue(result);
	}
	
	@Test
	public void load_Dealership_Should_Return_Car() {
		dealershipDAO.loadDealership();
		
		Dealership dealership = dealershipDAO.getDealership();
		Car car = dealership.getCars().get(0);
		
		assertEquals("The car is a Corolla", "Corolla", car.getModel());
	}

}
