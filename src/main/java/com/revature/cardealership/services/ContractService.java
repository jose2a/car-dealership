package com.revature.cardealership.services;

import java.util.Set;

import com.revature.cardealership.exceptions.NotFoundRecordException;
import com.revature.cardealership.model.Contract;
import com.revature.cardealership.model.Payment;

public interface ContractService {

	Set<Payment> getAllPayments();

	Set<Payment> getAllPaymentsForCustomer(String username) throws NotFoundRecordException;

	Set<Payment> getRemainingPayments(String contractId) throws NotFoundRecordException;
	Set<Payment> getRemainingPaymentsForCar(String vin, String usernamex) throws NotFoundRecordException;

	boolean makeAnOffer(String username, String carVin, double amount) throws NotFoundRecordException;

	void acceptOffer(String contractId) throws NotFoundRecordException;

	void rejectOffer(String contractId) throws NotFoundRecordException;

	Set<Contract> getPendingOffers();

	Set<Contract> getAllOffers();
}
