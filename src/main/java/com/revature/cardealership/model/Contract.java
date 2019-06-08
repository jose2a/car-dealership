package com.revature.cardealership.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Contract implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String contractId;
	private LocalDate signedDate;
	private double amount; // car's sold price
	private int totalPayments; // Total number of payments
	private int paymentsMade; // Number of payments made
	private double monthlyPayment; // amount to pay every month
	private ContractStatus status;

	private Customer customer;
	private Car car;

	public Contract() {
		// TODO Auto-generated constructor stub
	}

	public Contract(String contractId, LocalDate signedDate, double amount, int totalPayments, int paymentsMade,
			ContractStatus status, Customer customer, Car car) {
		super();
		this.contractId = contractId;
		this.signedDate = signedDate;
		this.amount = amount;
		this.totalPayments = totalPayments;
		this.monthlyPayment = 0.0;
		this.paymentsMade = paymentsMade;
		this.status = status;
		this.customer = customer;
		this.car = car;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public LocalDate getSignedDate() {
		return signedDate;
	}

	public void setSignedDate(LocalDate signedDate) {
		this.signedDate = signedDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getTotalPayments() {
		return totalPayments;
	}

	public void setTotalPayments(int totalPayments) {
		this.totalPayments = totalPayments;
	}

	public int getPaymentsMade() {
		return paymentsMade;
	}

	public void setPaymentsMade(int paymentsMade) {
		this.paymentsMade = paymentsMade;
	}

	public double getMonthlyPayment() {
		return monthlyPayment;
	}

	public void setMonthlyPayment(double monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}

	public ContractStatus getStatus() {
		return status;
	}

	public void setStatus(ContractStatus status) {
		this.status = status;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((contractId == null) ? 0 : contractId.hashCode());
		result = prime * result + ((signedDate == null) ? 0 : signedDate.hashCode());
		result = prime * result + totalPayments;
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
		Contract other = (Contract) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (contractId == null) {
			if (other.contractId != null)
				return false;
		} else if (!contractId.equals(other.contractId))
			return false;
		if (signedDate == null) {
			if (other.signedDate != null)
				return false;
		} else if (!signedDate.equals(other.signedDate))
			return false;
		if (totalPayments != other.totalPayments)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Offer No: " + contractId + "\nCustomer:" + customer + "\nCar: " + car + "\nSigned On: " + signedDate
				+ "\nAmount Offered: " + amount + "\nStatus: " + status.toString();
	}

}
