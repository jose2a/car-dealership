package com.revature.cardealership.model;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {

	private transient List<Car> cars = new ArrayList<>();
	private List<String> carVins = new ArrayList<>();

	private transient List<Offer> offers = new ArrayList<>();
	private List<String> offerIds = new ArrayList<>();

	private transient List<Contract> contracts = new ArrayList<>();
	private List<String> contractIds = new ArrayList<>();

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(String username, String password, String firstName, String lastName) {
		super(username, password, firstName, lastName);
		// TODO Auto-generated constructor stub
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	public void addCarToCustomer(Car car) {
		this.cars.add(car);
	}

	public void removeCar(Car car) {
		this.cars.remove(car);
	}

	public List<Offer> getOffers() {
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}

	public void addOffer(Offer offer) {
		this.offers.add(offer);
	}

	public void removeOffer(Offer offer) {
		this.offers.remove(offer);
	}

	public List<Contract> getContracts() {
		return contracts;
	}

	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
	}

	public List<String> getCarVins() {
		return carVins;
	}

	public void setCarVins(List<String> carVins) {
		this.carVins = carVins;
	}

	public List<String> getOfferIds() {
		return offerIds;
	}

	public void setOfferIds(List<String> offerIds) {
		this.offerIds = offerIds;
	}

	public List<String> getContractIds() {
		return contractIds;
	}

	public void setContractIds(List<String> contractIds) {
		this.contractIds = contractIds;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
