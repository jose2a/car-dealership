package com.revature.cardealership.services;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import com.revature.cardealership.dao.DealershipDAO;
import com.revature.cardealership.exceptions.NotFoundRecordException;
import com.revature.cardealership.model.Car;
import com.revature.cardealership.model.Contract;
import com.revature.cardealership.model.ContractStatus;
import com.revature.cardealership.model.Customer;
import com.revature.cardealership.model.Dealership;
import com.revature.cardealership.model.Payment;
import com.revature.cardealership.model.User;
import com.revature.cardealership.utils.DAOUtils;
import com.revature.cardealership.utils.LoggingUtil;

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

		// Get users from dealership object
		Iterator<User> userIter = this.dealership.getUsers().iterator();

		Customer customer = getCustomerByUsername(username, userIter);

		if (customer == null) {
			throw new NotFoundRecordException("Customer does not exist in the system.");
		}

		// Get cars from dealership object
		Iterator<Car> carIter = this.dealership.getCars().iterator();

		Car car = getCarByVinNumber(carVin, carIter);

		if (car == null) {
			throw new NotFoundRecordException("Car does not exist in the system.");
		}

		// Get new id for contract
		String contractId = getRandomContractId();

		// Create contract
		Contract contract = new Contract(contractId, LocalDate.now(), amount, 0, 0, ContractStatus.PENDING, customer,
				car);

		car.getContracts().add(contract);
		customer.getContracts().add(contract);

		if (dao.save()) {
			return true;
		}

		return false;
	}

	@Override
	public void acceptOffer(String contractId) throws NotFoundRecordException {
		// Access the contracts through the user collection
		Iterator<User> userIter = this.dealership.getUsers().iterator();

		Contract contract = findContractByContractId(contractId, userIter);
		
		if (contract == null) {
			throw new NotFoundRecordException("Contract does not exist in the system");
		}

		if (contract.getStatus() != ContractStatus.ACCEPTED) {
			contract.setStatus(ContractStatus.ACCEPTED);
		}

		userIter = this.dealership.getUsers().iterator();

		rejectContractsAndSkipAccepted(userIter, contract);

		this.dao.save();
	}

	@Override
	public void rejectOffer(String contractId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<Contract> getPendingOffers() {
		Set<Contract> pending = new HashSet<>();

		// Access the contracts through the user collection
		Iterator<User> userIter = this.dealership.getUsers().iterator();

		while (userIter.hasNext()) {
			User user = userIter.next();

			// Check if user is a Customer
			if (user instanceof Customer) {
				Customer customer = (Customer) user;

				// Access customers' contracts
				Iterator<Contract> contractIter = customer.getContracts().iterator();

				while (contractIter.hasNext()) {
					Contract contract = contractIter.next();

					// Check if contract is not accepted
					if (contract.getStatus() == ContractStatus.PENDING) {
						
						LoggingUtil.debug(contract.getContractId() + " vin: " + contract.getCar().getVin() + " status: "
								+ contract.getStatus());

						pending.add(contract);
					}
				}
			}
		}

		return pending;
	}

	// Generate random Id for contracts
	private String getRandomContractId() {
		String[] ids = UUID.randomUUID().toString().split("-");

		return ids[new Random().nextInt(ids.length)];
	}

	// Get customer
	private Customer getCustomerByUsername(String username, Iterator<User> userIter) {
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

	// Get car
	private Car getCarByVinNumber(String vinNumber, Iterator<Car> carIter) {
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

	// Validate data for car and customer
	private void validate(String username, String carVin) {
		if (username == null) {
			throw new IllegalArgumentException("Customer username should not be empty.");
		}

		if (carVin == null) {
			throw new IllegalArgumentException("Car vin number should not be empty.");
		}
	}

	private void rejectContractsAndSkipAccepted(Iterator<User> userIter, Contract contractToSkip) {

		while (userIter.hasNext()) {
			User user = userIter.next();

			// Check if user is a Customer
			if (user instanceof Customer) {
				Customer customer = (Customer) user;

				// Access customers' contracts
				Iterator<Contract> contractIter = customer.getContracts().iterator();

				while (contractIter.hasNext()) {
					Contract curr = contractIter.next();

					if (curr.getCar().equals(contractToSkip.getCar())
							&& curr.getContractId() != contractToSkip.getContractId()
							&& curr.getStatus() == ContractStatus.PENDING) {

						curr.setStatus(ContractStatus.REJECTED);
					}
				}
			}

		}
	}

	// Find contract by contractId
	private Contract findContractByContractId(String contractId, Iterator<User> userIter) {
		Contract contract = null;

		while (userIter.hasNext()) {
			User user = userIter.next();

			// Check if user is a Customer
			if (user instanceof Customer) {
				Customer customer = (Customer) user;

				// Access customers' contracts
				Iterator<Contract> contractIter = customer.getContracts().iterator();

				while (contractIter.hasNext()) {
					contract = contractIter.next();

					// Check if contract is in the system
					if (contract.getContractId().equals(contractId)) {
						LoggingUtil.debug(contract.getContractId() + " vin: " + contract.getCar().getVin()
								+ " accepted: " + contract.getStatus());
						break;
					}

					contract = null;
				}

				if (contract != null) {
					break;
				}
			}
		}

		return contract;
	}

}
