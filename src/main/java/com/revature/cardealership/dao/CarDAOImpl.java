package com.revature.cardealership.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.cardealership.exceptions.PreexistingRecordException;
import com.revature.cardealership.model.Car;

public class CarDAOImpl implements CarDAO {

	private static final Logger LOGGER = LogManager.getLogger(CarDAOImpl.class.getName());

	private String fileName;
	private Map<String, Car> cars;

	public CarDAOImpl(String fileName) {
		this.fileName = fileName;
		this.cars = new HashMap<>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean loadCars() {
		try (FileInputStream fis = new FileInputStream(fileName); 
				ObjectInputStream ois = new ObjectInputStream(fis);) {

			this.cars = (Map<String, Car>) ois.readObject();

			return true;
		} catch (FileNotFoundException e) {
			LOGGER.debug("File for the cars does not exist.");
		} catch (ClassNotFoundException e) {
			LOGGER.error(e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}

		return false;
	}

	@Override
	public void insertCar(Car car) throws PreexistingRecordException {
		if (cars.containsKey(car.getVin())) {
			throw new PreexistingRecordException("A car with the same ");
		}
		cars.put(car.getVin(), car);
	}

	@Override
	public void updateCar(Car car) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCar(Car car) {
		// TODO Auto-generated method stub

	}

	@Override
	public Car getCarByVin(String vin) {

		return cars.get(vin);
	}

	@Override
	public Set<Car> getAllCars() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save() {

		try (FileOutputStream fos = new FileOutputStream(this.fileName);
				ObjectOutputStream oos = new ObjectOutputStream(fos);) {

			oos.writeObject(this.cars);

			return true;

		} catch (FileNotFoundException e) {
			LOGGER.debug("File not found.");
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}

		return false;
	}

}
