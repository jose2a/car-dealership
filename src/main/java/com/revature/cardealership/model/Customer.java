package com.revature.cardealership.model;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Car> cars = new ArrayList<>();

	private List<Contract> contracts = new ArrayList<>();

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

	public List<Contract> getContracts() {
		return contracts;
	}

	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
	}
	
	public void addContract(Contract contract) {
		this.contracts.add(contract);
	}
	
	public void removeContract(Contract contract) {
		this.contracts.remove(contract);
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
