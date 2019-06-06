package com.revature.cardealership.model;

import java.util.List;

public class Dealership {

	private List<User> users;
	private List<Car> cars;

	public Dealership() {
		// TODO Auto-generated constructor stub
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
