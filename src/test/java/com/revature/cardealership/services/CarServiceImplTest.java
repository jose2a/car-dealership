package com.revature.cardealership.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.revature.cardealership.exceptions.PreexistingRecordException;
import com.revature.cardealership.model.Car;

public class CarServiceImplTest {

	private CarServiceImpl carService;

	@Before
	public void setUp() throws Exception {
		carService = new CarServiceImpl();
	}

	@Test
	public void addCar_When_Car_Is_Null_Should_Return_False() throws PreexistingRecordException {
		Car car = null;

		boolean result = carService.addCar(car);

		assertEquals(false, result);
	}

	@Test(expected = PreexistingRecordException.class)
	public void addCar_A_Car_With_Same_VIN_Already_Exist_Should_Trows_PreexistingRecordException()
			throws PreexistingRecordException {
		Car car = new Car("1111111", "Ford", "Focus", 23000, false);

		boolean result = carService.addCar(car);

		assertEquals(false, result);
	}

	@Test
	public void addCar_Car_Is_Valid_Should_Return_True() throws PreexistingRecordException {
		Car car = new Car("22222222222", "Ford", "Focus", 23000, false);

		boolean result = carService.addCar(car);

		assertEquals(true, result);
	}

	@Test
	public void getCars_Should_Return_List_Of_Cars() {
		List<Car> cars = new ArrayList<>();
		cars.add(new Car("1111111", "Toyota", "Corolla", 17000, false));
		cars.add(new Car("22222222222", "Ford", "Focus", 23000, false));
		
		assertEquals(cars, carService.getCars());
	}

	@Test
	public void testRemoveCar() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCarsByCustomerUsername() {
		fail("Not yet implemented");
	}

}
