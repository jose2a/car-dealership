package com.revature.cardealership.services;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.revature.cardealership.exceptions.NotFoundRecordException;

public class ContractServiceImplTest {

	private ContractServiceImpl service;

	@Before
	public void setUp() throws Exception {
		service = new ContractServiceImpl();
	}

	@Test(expected = IllegalArgumentException.class)
	public void makeAnOffer_UsernameIsNullShouldThrowIllegalArgumentException() throws NotFoundRecordException {
		service.makeAnOffer(null, "22222222222", 20000);
	}

	@Test(expected = IllegalArgumentException.class)
	public void makeAnOffer_VinIsNullShouldThrowIllegalArgumentException() throws NotFoundRecordException {
		service.makeAnOffer("user2", null, 20000);
	}

	@Test(expected = NotFoundRecordException.class)
	public void makeAnOffer_UserIsNotFoundShouldThrowNotFoundRecordException() throws NotFoundRecordException {
		service.makeAnOffer("user3", "22222222222", 20000);
	}

	@Test(expected = NotFoundRecordException.class)
	public void makeAnOffer_CarIsNotFoundShouldThrowNotFoundRecordException() throws NotFoundRecordException {
		service.makeAnOffer("user2", "2222222244", 20000);
	}

	@Test
	public void makeAnOffer_UserAndCarAreValidShouldReturnTrue() throws NotFoundRecordException {
		boolean result = service.makeAnOffer("user2", "22222222222", 20000);

		assertTrue(result);
	}

}
