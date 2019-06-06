package com.revature.cardealership.services;

import java.time.LocalDate;
import java.util.List;

import com.revature.cardealership.model.Contract;
import com.revature.cardealership.model.Payment;

public interface ContractService {

	public Contract createContract(String username, String vin, double amount, LocalDate signedDate);
	public List<Payment> payments(int contractId);
	public int getRemainingPayments(int contractId);
}
