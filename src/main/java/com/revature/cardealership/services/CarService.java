package com.revature.cardealership.services;

import java.util.List;

import com.revature.cardealership.model.Car;

public interface CarService {
	
	public boolean addCar(Car car);
	public List<Car> getCars();
	public boolean removeCar(String vin);
	public List<Car> getCarsByCustomerUsername(String username);
	
}
