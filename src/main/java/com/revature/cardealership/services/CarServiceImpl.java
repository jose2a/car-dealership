package com.revature.cardealership.services;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

import com.revature.cardealership.dao.DealershipDAO;
import com.revature.cardealership.exceptions.NotFoundRecordException;
import com.revature.cardealership.exceptions.PreexistingRecordException;
import com.revature.cardealership.model.Car;
import com.revature.cardealership.model.ContractStatus;
import com.revature.cardealership.model.Dealership;
import com.revature.cardealership.utils.DAOUtil;
import com.revature.cardealership.utils.LogUtil;

public class CarServiceImpl implements CarService {

	private DealershipDAO dao = DAOUtil.geDealershipDAO();
	private Dealership dealership;

	public CarServiceImpl() throws IOException {
		dao.loadDealership();
		this.dealership = dao.getDealership();
	}

	@Override
	public boolean addCar(Car car) throws PreexistingRecordException {
		if (car != null) {
			
			validateCar(car);
			
			if (!isCarAdded(car)) {
				dealership.addCar(car);

				return dao.save();
			}

			LogUtil.trace("Car with the same VIN already in the file.");
			throw new PreexistingRecordException("Car with the same VIN already in the file.");
		}

		return false;
	}

	private void validateCar(Car car) {
		if(car.getVin().isEmpty()) {
			throw new IllegalArgumentException("Vin number should not be empty");
		}
		
		if(car.getMake().isEmpty()) {
			throw new IllegalArgumentException("Make number should not be empty");
		}
		
		if (car.getModel().isEmpty()) {
			throw new IllegalArgumentException("Model number should not be empty");
		}
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
	public boolean removeCar(String vin) throws NotFoundRecordException, IllegalArgumentException {
		if (vin == null || vin.isEmpty()) {
			throw new IllegalArgumentException("VIN number should not be empty.");
		}

		Iterator<Car> carIter = this.dealership.getCars().iterator();

		Car car = null;

		while (carIter.hasNext()) {
			car = carIter.next();

			if (car.getVin().equals(vin)) {
				break;
			}

			car = null;
		}

		if (car == null) {
			LogUtil.trace("Car with VIN: " + vin + " was not found.");
			throw new NotFoundRecordException("Car with VIN: " + vin + " was not found.");
		}

		this.dealership.removeCar(car);

		return dao.save();
	}

	@Override
	public Set<Car> getCarsByCustomerUsername(String username) {
		if (username == null) {
			throw new IllegalArgumentException("Username should not be empty.");
		}

		Set<Car> carsForUser = new HashSet<Car>();

		Iterator<Car> carIter = this.dealership.getCars().iterator();

		while (carIter.hasNext()) {
			Car car = carIter.next();
			Set<Car> cars = car.getContracts().stream().filter(
					c -> c.getCustomer().getUsername().equals(username) && c.getStatus() == ContractStatus.ACCEPTED)
					.map(c -> c.getCar()).collect(Collectors.toSet());

			carsForUser.addAll(cars);
		}

		return carsForUser;
	}

}
