package com.revature.cardealership.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.revature.cardealership.exceptions.PreexistingRecordException;
import com.revature.cardealership.model.Car;

public class CarDAOImplTest {
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	private static final String FILE_NAME = "car.dat";
	
	private CarDAOImpl carDao;
	
	@Before
	public void setup() {
		carDao = new CarDAOImpl(FILE_NAME);
		carDao.loadCars();
	}
	

	@Test
	public void init_File_Does_No_Exist_Returns_False() {		
		assertFalse(carDao.loadCars());
	}
	
	@Test
	public void init_File_Exist_Returns_True() {		
		assertTrue(carDao.loadCars());
	}

	@Test
	public void insertCar_Returns_True_When_Save() throws PreexistingRecordException {
		Car theCar = new Car("1111111", "Toyota", "Camry", 15000.0, false);
		carDao.insertCar(theCar);
		
		assertTrue(carDao.save());
	}
	
	@Test(expected = PreexistingRecordException.class)
	public void insertCar_Throws_PreexistingRecordException() throws PreexistingRecordException {
		Car theCar = new Car("1111111", "Toyota", "Camry", 15000.0, false);
		carDao.insertCar(theCar);
		
		assertFalse(carDao.save());
	}

	@Test
	public void updateCar_Returns_True_When_Save() {
		Car theCar = new Car("1111111", "Toyota", "Corolla", 10000.0, false);
		carDao.updateCar(theCar);
		
		assertTrue(carDao.save());
		
		carDao.loadCars();
		
		theCar = carDao.getCarByVin("1111111");
		
		assertEquals("Corolla", theCar.getModel());
	}

	@Test
	public void deleteCar_Retuns_True_When_Save() {
		Car theCar = carDao.getCarByVin("1111111");
		carDao.deleteCar(theCar);
		
		assertTrue(carDao.save());
		
		carDao.loadCars();
		
		theCar = carDao.getCarByVin("1111111");
		
		assertEquals(null, theCar);
	}
	
	@Test
	public void deleteCar_Car_Dont_Exist_Retuns_False_When_Save() {
		Car theCar = new Car();
		theCar.setVin("2222222");
		carDao.deleteCar(theCar);
		
		assertFalse(carDao.save());
	}

	@Test
	public void getCarByVin_Car_In_File_Returns_Car() {
		Car theCar = carDao.getCarByVin("1111111");
		
		assertEquals("1111111", theCar.getVin());
		assertEquals("Toyota", theCar.getMake());
	}
	
	@Test
	public void getCarByVin_Car_Not_In_File_Returns_Null() {
		Car theCar = carDao.getCarByVin("2222222");
		
		assertEquals(null, theCar);
	}

	@Test
	public void getAllCars_Returns_All_Cars() {
		Map<String, Car> cars = new HashMap<>();
		Car theCar = new Car("1111111", "Toyota", "Camry", 15000.0, false);
		
		cars.put("1111111", theCar);
	}

	@Test
	public void testSave() {
		fail("Not yet implemented");
	}

}
