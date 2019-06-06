package com.revature.cardealership.model;

public class Employee extends User {

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(String username, String password, String firstName, String lastName) {
		super(username, password, firstName, lastName);
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
