package com.revature.cardealership.services;

import java.io.IOException;
import java.util.Set;

import com.revature.cardealership.dao.DealershipDAO;
import com.revature.cardealership.exceptions.NotFoundRecordException;
import com.revature.cardealership.exceptions.PreexistingRecordException;
import com.revature.cardealership.model.Car;
import com.revature.cardealership.model.Dealership;
import com.revature.cardealership.utils.DAOUtils;
import com.revature.cardealership.utils.LogginUtil;

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
//				dao.setDealership(dealership);

				return dao.save();
			}

			LogginUtil.trace("Car with the same VIN already in the file.");
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
		return false;
	}

	@Override
	public Set<Car> getCarsByCustomerUsername(String username) {
		return null;
	}

}
