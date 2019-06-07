package com.revature.cardealership.model;

import java.time.LocalDateTime;

public class Payment {

	private int paymentNo;
	private String customerInfo;
	private String carInfo;
	private LocalDateTime paidDate;
	private double amountPaid;

	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Payment(int paymentNo, String customerInfo, String carInfo, LocalDateTime paidDate, double amountPaid) {
		super();
		this.paymentNo = paymentNo;
		this.customerInfo = customerInfo;
		this.carInfo = carInfo;
		this.paidDate = paidDate;
		this.amountPaid = amountPaid;
	}

	public int getPaymentNo() {
		return paymentNo;
	}

	public void setPaymentNo(int paymentNo) {
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

	public LocalDateTime getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(LocalDateTime paidDate) {
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
				+ paidDate + ", Amount: " + amountPaid;
	}

}
