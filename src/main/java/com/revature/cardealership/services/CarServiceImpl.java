package com.revature.cardealership.services;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.revature.cardealership.dao.DealershipDAO;
import com.revature.cardealership.exceptions.NotFoundRecordException;
import com.revature.cardealership.exceptions.PreexistingRecordException;
import com.revature.cardealership.model.Car;
import com.revature.cardealership.model.Dealership;
import com.revature.cardealership.utils.DAOUtils;
import com.revature.cardealership.utils.LoggingUtil;

public class CarServiceImpl implements CarService {

	private DealershipDAO dao = DAOUtils.geDealershipDAO();
	private Dealership dealership;

	public CarServiceImpl() throws IOException {
		dao.loadDealership();
		this.dealership = dao.getDealership();
	}

	@Override
	public boolean addCar(Car car) throws PreexistingRecordException {
		if (car != null) {
			if (!isCarAdded(car)) {
				dealership.addCar(car);

				return dao.save();
			}

			LoggingUtil.trace("Car with the same VIN already in the file.");
			throw new PreexistingRecordException();
		}

		return false;
	}

	private boolean isCarAdded(Car car) {
		for (Car c : this.dealership.getCars()) {
			if (c.getVin().equals(car.getVin())) {
				return true;
			}
		}

		return false;
	}

	@Override
	public Set<Car> getCars() {
		return this.dealership.getCars();
	}

	@Override
	public boolean removeCar(String vin) throws NotFoundRecordException {
		if (vin == null || vin.isEmpty()) {
			throw new IllegalArgumentException("VIN number should not be empty.");
		}

		Iterator<Car> carIter = this.dealership.getCars().iterator();
		boolean isFound = false;

		Car car = null;

		while (carIter.hasNext()) {
			car = carIter.next();

			if (car.getVin().equals(vin)) {
				isFound = true;
				break;
			}
		}

		if (!isFound) {
			LoggingUtil.trace("Car with VIN: " + vin + " was not found.");
			throw new NotFoundRecordException("Car with VIN: " + vin + " was not found.");
		}

		this.dealership.removeCar(car);

		return true;
	}

	@Override
	public Set<Car> getCarsByCustomerUsername(String username) {
		if (username == null) {
			throw new IllegalArgumentException("Username should not be empty.");
		}

		Set<Car> carsForUser = new HashSet<Car>();

		Iterator<Car> carIter = this.dealership.getCars().iterator();

		while (carIter.hasNext()) {
			Car car = (Car) carIter.next();

			if (car.getCustomer() != null && car.getCustomer().getUsername().equals(username)) {
				carsForUser.add(car);

			}
		}

		return carsForUser;
	}

}
