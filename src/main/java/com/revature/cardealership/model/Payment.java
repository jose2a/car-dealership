package com.revature.cardealership.model;

import java.time.LocalDate;

@SuppressWarnings("rawtypes")
public class Payment implements Comparable {

	private Integer paymentNo;
	private String customerInfo;
	private String carInfo;
	private LocalDate paidDate;
	private double amountPaid;

	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Payment(Integer paymentNo, String customerInfo, String carInfo, LocalDate paidDate, double amountPaid) {
		super();
		this.paymentNo = paymentNo;
		this.customerInfo = customerInfo;
		this.carInfo = carInfo;
		this.paidDate = paidDate;
		this.amountPaid = amountPaid;
	}

	public Integer getPaymentNo() {
		return paymentNo;
	}

	public void setPaymentNo(Integer paymentNo) {
		this.paymentNo = paymentNo;
	}

	public String getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(String customerInfo) {
		this.customerInfo = customerInfo;
	}

	public String getCarInfo() {
		return carInfo;
	}

	public void setCarInfo(String carInfo) {
		this.carInfo = carInfo;
	}

	public LocalDate getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(LocalDate paidDate) {
		this.paidDate = paidDate;
	}

	public double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

	@Override
	public String toString() {
		return "Payment No: " + paymentNo + ", Customer: " + customerInfo + ", Car: " + carInfo + ", Paid On: "
				+ (paidDate == null ? "--------" : paidDate) + ", Amount: " + String.format("%1$,.2f", amountPaid);
	}

	@Override
	public int compareTo(Object o) {
		return paymentNo.compareTo(((Payment)o).getPaymentNo());
	}

}
