package com.revature.cardealership.services;

import java.util.Set;

import com.revature.cardealership.exceptions.NotFoundRecordException;
import com.revature.cardealership.model.Contract;
import com.revature.cardealership.model.Payment;

public interface ContractService {

	Set<Payment> getAllPaymentsForCustomer(String username);

	Set<Payment> getRemainingPayments(int contractId);

	boolean makeAnOffer(String username, String carVin, double amount) throws NotFoundRecordException;

	void acceptOffer(String contractId) throws NotFoundRecordException;

	void rejectOffer(String contractId);

	Set<Contract> getPendingOffers();
}
