package com.revature.cardealership.ui.controller;

public enum MenuOptions {
	REGISTER, LOGIN, EXIT, // Available to both user
	MENU_EMPLOYEE, LIST_CARS_EMPLOYEE, ADD_CAR, REMOVE_CAR, LIST_OFFER_EMPLOYEE, ACCEPT_OFFER, REJECT_OFFER, LIST_PAYMENTS_EMPLOYEE, // Employee options
	MENU_CUSTOMER, LIST_CARS_CUSTOMER, MAKE_OFFER, MY_CARS_LIST, REMAINING_PAYMENTS // Customer options
}
