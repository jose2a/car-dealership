package com.revature.cardealership.services;

import java.util.List;

import com.revature.cardealership.dao.DealershipDAO;
import com.revature.cardealership.exceptions.PreexistingRecordException;
import com.revature.cardealership.model.Car;
import com.revature.cardealership.model.Dealership;
import com.revature.cardealership.utils.DAOUtils;

public class CarServiceImpl implements CarService {

	private DealershipDAO dao = DAOUtils.geDealershipDAO();
	private Dealership dealership;

	public CarServiceImpl() {
		if (dao.loadDealership()) {
			this.dealership = dao.getDealership();
		} else {
			this.dealership = new Dealership();
			this.dao.setDealership(dealership);
			this.dao.save();
		}
	}

	@Override
	public boolean addCar(Car car) throws PreexistingRecordException {		
		if (car != null) {
			if (!isCarAdded(car)) {
				dealership.addCar(car);
				dao.setDealership(dealership);

				return dao.save();
			}
			
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
	public List<Car> getCars() {
		return this.dealership.getCars();
	}

	@Override
	public boolean removeCar(String vin) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Car> getCarsByCustomerUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
