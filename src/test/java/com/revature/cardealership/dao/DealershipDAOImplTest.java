package com.revature.cardealership.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Iterator;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.revature.cardealership.model.Car;
import com.revature.cardealership.model.Dealership;
import com.revature.cardealership.model.User;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DealershipDAOImplTest {

	private static final String FILE_NAME = "dealership.dat";
	private static final String DEALERSHIP_NAME = "ABC Autosales";
	
	private DealershipDAO dealershipDAO;

	@Before
	public void setUp() throws Exception {
		dealershipDAO = new DealershipDAOImpl(FILE_NAME, DEALERSHIP_NAME);
	}

	@Test
	public void _1_init_File_Does_No_Exist_Returns_False() throws IOException {	
		assertFalse(dealershipDAO.loadDealership());
	}
	
	@Test
	public void _2_init_File_Exist_Returns_True() throws IOException {		
		assertTrue(dealershipDAO.loadDealership());
	}
	
	@Test
	public void _3_getDealership_Should_Return_Dealership() throws IOException {
		dealershipDAO.loadDealership();
		
		Dealership dealership = dealershipDAO.getDealership();
		
		String expected = DEALERSHIP_NAME;
		
		assertEquals(expected, dealership.getName());
	}
	
	@Test
	public void _4_save_Cart_To_File_Should_Return_True() throws IOException {
		dealershipDAO.loadDealership();
		
		Dealership dealership = dealershipDAO.getDealership();
		dealership.addCar(new Car("1111111", "Toyota", "Corolla", 17000, false));
		dealership.addUser(new User("user1", "s3cret", "John", "Doe"));
		
		boolean result = dealershipDAO.save();
		
		assertTrue(result);
	}
	
	@Test
	public void _5_load_Dealership_Should_Return_Car() throws IOException {
		dealershipDAO.loadDealership();
		
		Dealership dealership = dealershipDAO.getDealership();
		Iterator<Car> iterator = dealership.getCars().iterator();
		
		Car car = null;
		
		while(iterator.hasNext()) {
			car = iterator.next();
			break;	
		}
		
		assertEquals("The car is a Corolla", "Corolla", car.getModel());
	}

}
