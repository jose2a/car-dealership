package com.revature.cardealership.services;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import com.revature.cardealership.dao.DealershipDAO;
import com.revature.cardealership.exceptions.NotFoundRecordException;
import com.revature.cardealership.model.Car;
import com.revature.cardealership.model.Contract;
import com.revature.cardealership.model.Customer;
import com.revature.cardealership.model.Dealership;
import com.revature.cardealership.model.Payment;
import com.revature.cardealership.model.User;
import com.revature.cardealership.utils.DAOUtils;

public class ContractServiceImpl implements ContractService {

	private DealershipDAO dao = DAOUtils.geDealershipDAO();
	private Dealership dealership;

	public ContractServiceImpl() throws IOException {
		dao.loadDealership();
		this.dealership = dao.getDealership();
	}

	@Override
	public Set<Payment> getAllPaymentsForCustomer(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Payment> getRemainingPayments(int contractId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean makeAnOffer(String username, String carVin, double amount) throws NotFoundRecordException {
		validate(username, carVin);

		Iterator<User> userIter = this.dealership.getUsers().iterator();

		Customer customer = getCustomer(username, userIter);

		if (customer == null) {
			throw new NotFoundRecordException("Customer does not exist in the system.");
		}

		Iterator<Car> carIter = this.dealership.getCars().iterator();

		Car car = getCar(carVin, carIter);

		if (car == null) {
			throw new NotFoundRecordException("Car does not exist in the system.");
		}

		String contractId = getRandomId();

		Contract contract = new Contract(contractId, LocalDate.now(), amount, 0, 0, false, customer,
				car);

		car.getContracts().add(contract);
		customer.getContracts().add(contract);

		if (dao.save()) {
			return true;
		}

		return false;
	}

	private String getRandomId() {
		String[] ids = UUID.randomUUID().toString().split("-");

		return ids[new Random().nextInt(ids.length)];
	}

	private Customer getCustomer(String username, Iterator<User> userIter) {
		Customer customer = null;

		while (userIter.hasNext()) {
			User user = userIter.next();

			if (user instanceof Customer) {

				customer = (Customer) user;

				if (customer.getUsername().equals(username)) {
					break;
				}
			}

			customer = null;
		}
		return customer;
	}

	private Car getCar(String vinNumber, Iterator<Car> carIter) {
		Car car = null;

		while (carIter.hasNext()) {
			car = carIter.next();

			if (car.getVin().equals(vinNumber)) {
				break;
			}

			car = null;
		}
		return car;
	}

	private void validate(String username, String carVin) {
		if (username == null) {
			throw new IllegalArgumentException("Customer username should not be empty.");
		}

		if (carVin == null) {
			throw new IllegalArgumentException("Car vin number should not be empty.");
		}
	}

	@Override
	public void acceptOffer(int contractId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void rejectOffer(int contractId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<Contract> getNotAcceptedOffers() {
		// TODO Auto-generated method stub
		return null;
	}

}
