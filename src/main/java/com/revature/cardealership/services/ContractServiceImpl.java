package com.revature.cardealership.services;

import java.io.IOException;
import java.util.Set;

import com.revature.cardealership.dao.DealershipDAO;
import com.revature.cardealership.model.Dealership;
import com.revature.cardealership.model.Payment;
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
	public boolean makeAnOffer(String username, String carVin, double amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void acceptOffer(int contractId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rejectOffer(int contractId) {
		// TODO Auto-generated method stub
		
	}

}
