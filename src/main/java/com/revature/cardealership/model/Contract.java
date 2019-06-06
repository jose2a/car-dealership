package com.revature.cardealership.model;

import java.time.LocalDate;
import java.util.List;

public class Contract {

	private String contractId;
	private LocalDate signedDate;
	private double amount;
	private int totalMonths;

	private transient Customer customer;
	private String customerId;

	private transient Car car;
	private String vin;

	private transient List<Payment> payments;
	private List<String> paymentIds;

	public Contract() {
		// TODO Auto-generated constructor stub
	}

	public Contract(String contractId, LocalDate signedDate, double amount, int totalMonths, String customerId,
			String vin) {
		super();
		this.contractId = contractId;
		this.signedDate = signedDate;
		this.amount = amount;
		this.totalMonths = totalMonths;
		this.customerId = customerId;
		this.vin = vin;
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

	public int getTotalMonths() {
		return totalMonths;
	}

	public void setTotalMonths(int totalMonths) {
		this.totalMonths = totalMonths;
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

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public List<String> getPaymentIds() {
		return paymentIds;
	}

	public void setPaymentIds(List<String> paymentIds) {
		this.paymentIds = paymentIds;
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
		result = prime * result + totalMonths;
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
		if (totalMonths != other.totalMonths)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Contract [idContract=" + contractId + ", signedDate=" + signedDate + ", amount=" + amount
				+ ", totalMonths=" + totalMonths + ", customer=" + customer + ", car=" + car + "]";
	}

}
