package com.revature.cardealership.dao;

import java.util.List;

import com.revature.cardealership.exceptions.PreexistingRecordException;
import com.revature.cardealership.model.Car;

public interface CarDAO {
	
	public boolean loadCars();
	
	public void insertCar(Car car) throws PreexistingRecordException;
	public void updateCar(Car car);
	public void deleteCar(Car car);
	
	public Car getCarByVin(String vin);
	public List<Car> getAllCars();
	
	public boolean save();

}
