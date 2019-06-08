package com.revature.cardealership.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Dealership implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private Set<User> users = new HashSet<>();
	private Set<Car> cars = new HashSet<>();

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

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public void addUser(User user) {
		users.add(user);
	}

	public void removeUser(User user) {
		if (users != null) {
			this.users.remove(user);
		}
	}

	public Set<Car> getCars() {
		return cars;
	}

	public void setCars(Set<Car> cars) {
		this.cars = cars;
	}

	public void addCar(Car car) {
		cars.add(car);
	}

	public void removeCar(Car car) {
		if (cars != null) {
			this.cars.remove(car);
		}
	}

	@Override
	public String toString() {
		return name;
	}
	
	

}
