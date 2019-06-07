package com.revature.cardealership.services;

import java.time.LocalDate;
import java.util.Set;

import com.revature.cardealership.model.Contract;

public interface ContractService {

	Contract createContract(String username, String vin, double amount, LocalDate signedDate);

	Set<Contract> getAllPaymentsForCustomer(String username);

	Contract getRemainingPayments(int contractId);

	boolean makeAnOffer(String username, String carVin, double amount);

	void acceptOffer(int contractId);

	void rejectOffer(int contractId);
}
