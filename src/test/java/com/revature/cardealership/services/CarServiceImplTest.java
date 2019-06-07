package com.revature.cardealership.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.revature.cardealership.exceptions.NotFoundRecordException;
import com.revature.cardealership.exceptions.PreexistingRecordException;
import com.revature.cardealership.model.Car;
import com.revature.cardealership.model.Customer;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CarServiceImplTest {

	private CarServiceImpl carService;

	@Before
	public void setUp() throws Exception {
		carService = new CarServiceImpl();
	}

	@Test
	public void _1_addCar_When_Car_Is_Null_Should_Return_False() throws PreexistingRecordException {
		Car car = null;

		boolean result = carService.addCar(car);

		assertEquals(false, result);
	}

	@Test(expected = PreexistingRecordException.class)
	public void _2_addCar_A_Car_With_Same_VIN_Already_Exist_Should_Trows_PreexistingRecordException()
			throws PreexistingRecordException {

		Car car = new Car("1111111", "Ford", "Focus", 23000, false);

		boolean result = carService.addCar(car);

		assertEquals(false, result);
	}

	@Test
	public void _3_addCar_Car_Is_Valid_Should_Return_True() throws PreexistingRecordException {
		Car car = new Car("22222222222", "Ford", "Focus", 23000, false);
		boolean result = carService.addCar(car);

		assertEquals(true, result);

		Customer theCustomer = new Customer("cust1", "s3cret", "John", "Doe");
		car = new Car("3333333", "Dodge", "Charger", 35000, false);
		theCustomer.addCarToCustomer(car);
		car.setCustomer(theCustomer);

		result = carService.addCar(car);

		assertEquals(true, result);
	}

	@Test
	public void _4_getCars_Should_Return_List_Of_Cars() {
		List<Car> cars = new ArrayList<>();
		cars.add(new Car("1111111", "Toyota", "Corolla", 17000, false));
		cars.add(new Car("22222222222", "Ford", "Focus", 23000, false));

		assertEquals(cars, carService.getCars());
	}

	@Test
	public void _5_removeCar_Retuns_True_When_Save() throws NotFoundRecordException {
		boolean removedCar = carService.removeCar("1111111");

		assertTrue(removedCar);
	}

	@Test(expected = NotFoundRecordException.class)
	public void _6_removeCar_Car_Doesnt_Exist_Retuns_False_When_Save() throws NotFoundRecordException {

		boolean removedCar = carService.removeCar("1111122");

		assertFalse(removedCar);
	}

	@Test(expected = IllegalArgumentException.class)
	public void _7_removeCar_Vin_Is_Null_Retuns_False() throws NotFoundRecordException {
		boolean removedCar = carService.removeCar("1111122");

		assertFalse(removedCar);
	}

	@Test(expected = IllegalArgumentException.class)
	public void _8_getCarsByCustomerUsername_Username_Is_Null() {
		carService.getCarsByCustomerUsername(null);
	}

	@Test
	public void _9_getCarsByCustomerUsername_Return_Cars() {

		Iterator<Car> iterator = carService.getCarsByCustomerUsername("cust1").iterator();

		Car car = null;

		while (iterator.hasNext()) {
			car = iterator.next();
			break;
		}

		assertEquals("Charger", car.getModel());
	}

}
