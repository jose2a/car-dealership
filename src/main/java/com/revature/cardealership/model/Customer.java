package com.revature.cardealership.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Customer extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Set<Car> cars = new HashSet<>();

	private Set<Contract> contracts = new HashSet<>();

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(String username, String password, String firstName, String lastName) {
		super(username, password, firstName, lastName);
		// TODO Auto-generated constructor stub
	}

	public Set<Car> getCars() {
		return cars;
	}

	public void setCars(Set<Car> cars) {
		this.cars = cars;
	}

	public void addCarToCustomer(Car car) {
		this.cars.add(car);
	}

	public void removeCar(Car car) {
		this.cars.remove(car);
	}

	public Set<Contract> getContracts() {
		return contracts;
	}

	public void setContracts(Set<Contract> contracts) {
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
