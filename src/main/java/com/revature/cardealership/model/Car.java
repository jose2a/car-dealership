package com.revature.cardealership.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Car implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String vin; // VIN number
	private String make; // Make
	private String model; // Model
	private double price; // Price
	private boolean isSold; // Is the car sold?

	private Customer customer;

	private Set<Contract> contracts = new HashSet<>();

	public Car() {
		// TODO Auto-generated constructor stub
	}

	public Car(String vin, String make, String model, double price, boolean isSold) {
		super();
		this.vin = vin;
		this.make = make;
		this.model = model;
		this.price = price;
		this.isSold = isSold;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isSold() {
		return isSold;
	}

	public void setSold(boolean isSold) {
		this.isSold = isSold;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Set<Contract> getContracts() {
		return contracts;
	}

	public void setContracts(Set<Contract> contracts) {
		this.contracts = contracts;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isSold ? 1231 : 1237);
		result = prime * result + ((make == null) ? 0 : make.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((vin == null) ? 0 : vin.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (isSold != other.isSold)
			return false;
		if (make == null) {
			if (other.make != null)
				return false;
		} else if (!make.equals(other.make))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (vin == null) {
			if (other.vin != null)
				return false;
		} else if (!vin.equals(other.vin))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "VIN number: " + vin + "\nMake: " + make + "\nModel: " + model + "\nPrice: " + price + "\nSold: " + isSold;
	}
	
	public String toSingleLineString() {
		return "VIN number: " + vin + ", Make: " + make + ", Model: " + model + ", Price: " + price + ", Sold: " + isSold;
	}

}
