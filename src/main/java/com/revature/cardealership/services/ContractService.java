package com.revature.cardealership.services;

import java.util.Set;

import com.revature.cardealership.exceptions.NotFoundRecordException;
import com.revature.cardealership.model.Contract;
import com.revature.cardealership.model.Payment;

public interface ContractService {

	Set<Payment> getAllPaymentsForCustomer(String username);

	Set<Payment> getRemainingPayments(int contractId);

	boolean makeAnOffer(String username, String carVin, double amount) throws NotFoundRecordException;

	void acceptOffer(int contractId);

	void rejectOffer(int contractId);
	
	Set<Contract> getNotAcceptedOffers();
}
