package com.revature.cardealership.model;

import java.io.Serializable;
import java.util.List;

public class Dealership implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private List<User> users;
	private List<Car> cars;

	public Dealership() {
		// TODO Auto-generated constructor stub
	}

	public Dealership(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

}
