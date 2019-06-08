package com.revature.cardealership.services;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
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
	private static final int TOTAL_CONTRACT_MONTHS = 36;

	public ContractServiceImpl() throws IOException {
		dao.loadDealership();
		this.dealership = dao.getDealership();
	}

	@Override
	public Set<Payment> getAllPayments() {
		Iterator<User> userIter = this.dealership.getUsers().iterator();

		Iterator<Contract> contractIter = getContractsByStatus(userIter, ContractStatus.ALL).iterator();

		return getPaymentsFromContracts(contractIter);
	}

	@Override
	public Set<Payment> getAllPaymentsForCustomer(String username) throws NotFoundRecordException {
		if (username == null) {
			throw new IllegalArgumentException("Please select a valid customer username.");
		}
		
		Iterator<User> userIter = this.dealership.getUsers().iterator();
		Customer customer = getCustomerByUsername(username, userIter);
		
		if (customer == null) {
			throw new NotFoundRecordException("The customer is not in the system.");
		}
		
		Set<Payment> paymentsFromContracts = getPaymentsFromContracts(customer.getContracts().iterator());
		
		return paymentsFromContracts;
	}

	@Override
	public Set<Payment> getRemainingPayments(String contractId) {
		if (contractId == null) {
			throw new IllegalArgumentException("Please select a valid offer number.");
		}
		
		Set<Payment> payments = new TreeSet<>();

		Iterator<User> userIter = this.dealership.getUsers().iterator();

		Contract contract = findContractByContractId(contractId, userIter);

		// Get all the payments made to a contract
		payments.addAll(getPaymentForContract(contract));

		// Adding the contract's remaining payments to the list
		for (int i = contract.getPaymentsMade(); i < contract.getTotalPayments(); i++) {
			Payment payment = new Payment(i + 1, contract.getCustomer().toString(), contract.getCar().toString(), null,
					0.0);

			payments.add(payment);
		}

		return payments;
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

		// Create contract, set it to PENDING
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

		if (contractId == null) {
			throw new IllegalArgumentException("Contract id should not be empty.");
		}

		// Access the contracts through the user collection
		Iterator<User> userIter = this.dealership.getUsers().iterator();

		Contract contract = findContractByContractId(contractId, userIter);

		if (contract == null) {
			throw new NotFoundRecordException("Offer does not exist in the system");
		}

		if (contract.getStatus() == ContractStatus.REJECTED) {
			throw new IllegalArgumentException("This offer was already rejected.");
		}

		// If the offer was accepted, we don't need to accept it again
		if (contract.getStatus() != ContractStatus.ACCEPTED) {
			contract.setStatus(ContractStatus.ACCEPTED);
			contract.setTotalPayments(TOTAL_CONTRACT_MONTHS);

			double monthlyPayment = contract.getAmount() / TOTAL_CONTRACT_MONTHS;
			contract.setMonthlyPayment(monthlyPayment);

			userIter = this.dealership.getUsers().iterator();

			rejectContractsAndSkipAccepted(userIter, contract);

			this.dao.save();
		}
	}

	@Override
	public void rejectOffer(String contractId) throws NotFoundRecordException {
		if (contractId == null) {
			throw new IllegalArgumentException("Contract id should not be empty.");
		}

		// Access the contracts through the user collection
		Iterator<User> userIter = this.dealership.getUsers().iterator();

		Contract contract = findContractByContractId(contractId, userIter);

		if (contract == null) {
			throw new NotFoundRecordException("Offer does not exist in the system");
		}

		if (contract.getStatus() == ContractStatus.ACCEPTED) {
			throw new IllegalArgumentException("This offer was already acepted.");
		}

		// If the contract was rejected we don't need to reject it and save it again
		if (contract.getStatus() != ContractStatus.REJECTED) {
			contract.setStatus(ContractStatus.REJECTED);

			this.dao.save();
		}
	}

	@Override
	public Set<Contract> getPendingOffers() {
		// Access the contracts through the user collection
		Iterator<User> userIter = this.dealership.getUsers().iterator();

		return getContractsByStatus(userIter, ContractStatus.PENDING);
	}

	@Override
	public Set<Contract> getAllOffers() {
		// Access the contracts through the user collection
		Iterator<User> userIter = this.dealership.getUsers().iterator();

		return getContractsByStatus(userIter, ContractStatus.ALL);
	}

	// Get contracts that meet the status
	private Set<Contract> getContractsByStatus(Iterator<User> userIter, ContractStatus status) {
		Set<Contract> contracts = new HashSet<>();

		while (userIter.hasNext()) {
			User user = userIter.next();

			// Check if user is a Customer
			if (user instanceof Customer) {
				Customer customer = (Customer) user;

				// If we select all, we didn't filter the contracts
				if (status == ContractStatus.ALL) {
					contracts.addAll(customer.getContracts());
					break;
				}

				// Access customers' contracts
				Iterator<Contract> contractIter = customer.getContracts().iterator();

				while (contractIter.hasNext()) {
					Contract contract = contractIter.next();

					// Check if contract is not accepted
					if (contract.getStatus() == status) {

						LoggingUtil.debug(contract.toString());

						contracts.add(contract);
					}
				}
			}
		}

		return contracts;
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

	// Reject all the offers except the one that is specified
	// This method is useful when we accept an offer
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
	
	// Get all the payments for the list of contracts
	private Set<Payment> getPaymentsFromContracts(Iterator<Contract> contractIter) {
		Set<Payment> payments = new HashSet<>();

		while (contractIter.hasNext()) {
			Contract contract = contractIter.next();

			payments.addAll(getPaymentForContract(contract));

		}
		return payments;
	}

	// Get payment for an specific contract
	private Set<Payment> getPaymentForContract(Contract contract) {
		Set<Payment> payments = new HashSet<>();

		for (int i = 0; i < contract.getPaymentsMade(); i++) {
			LocalDate paidDate = contract.getSignedDate().plusMonths(i);

			Payment payment = new Payment(i + 1, contract.getCustomer().toString(), contract.getCar().toString(),
					paidDate, contract.getMonthlyPayment());
			payments.add(payment);
		}

		return payments;
	}

}
