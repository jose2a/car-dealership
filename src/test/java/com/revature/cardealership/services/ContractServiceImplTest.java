package com.revature.cardealership.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.revature.cardealership.exceptions.NotFoundRecordException;
import com.revature.cardealership.model.Contract;
import com.revature.cardealership.model.ContractStatus;

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

		result = service.makeAnOffer("user2", "3333333", 24000);

		assertTrue(result);
	}

	@Test(expected = NotFoundRecordException.class)
	public void acceptOffer_ContractIsNotInTheSystem_ShouldThrowNotFoundRecordException()
			throws NotFoundRecordException {
		service.acceptOffer("11234");
	}

	@Test
	public void acceptOffer_ContractIsValidTheContractIsAcceptedChangedSystem_ShouldThrowNotFoundRecordException()
			throws NotFoundRecordException {
		service.acceptOffer("f0f90158-c7b5-4f5b-bab9-8f6a5a94e04b");

		Iterator<Contract> resIter = service.getPendingOffers().iterator();

		Contract result = null;

		while (resIter.hasNext()) {
			result = resIter.next();
			assertTrue(result.getStatus() == ContractStatus.PENDING || result.getStatus() == ContractStatus.REJECTED);
		}

	}

	@Test
	public void getPendingOffers_ShouldReturnContractsWithIsAcceptedEqualsFalse() {
		Set<Contract> contracts = service.getPendingOffers();

		Iterator<Contract> noAccepted = contracts.iterator();

		while (noAccepted.hasNext()) {
			Contract contract = noAccepted.next();

			assertFalse(contract.getStatus() == ContractStatus.PENDING);

		}
	}

}
