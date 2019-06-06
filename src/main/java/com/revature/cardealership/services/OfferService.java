package com.revature.cardealership.services;

import com.revature.cardealership.model.Car;
import com.revature.cardealership.model.Customer;
import com.revature.cardealership.model.Offer;

public interface OfferService {

	public boolean makeAnOffer(Customer customer, Car car, double amount);

	public void acceptOffer(Offer offer);

	public void rejectOffer(Offer offer);

}
