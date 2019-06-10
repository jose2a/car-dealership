package com.revature.cardealership.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.revature.cardealership.exceptions.NotFoundRecordException;
import com.revature.cardealership.model.Contract;
import com.revature.cardealership.model.ContractStatus;
import com.revature.cardealership.model.Payment;

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

	@Test(expected = IllegalArgumentException.class)
	public void acceptOffer_ContractIdIsNull_ShouldThrowIllegalArgumentException() throws NotFoundRecordException {
		service.acceptOffer(null);
	}

	@Test(expected = NotFoundRecordException.class)
	public void acceptOffer_ContractIsNotInTheSystem_ShouldThrowNotFoundRecordException()
			throws NotFoundRecordException {
		String contractId = "11234";
		service.acceptOffer(contractId);
	}

	@Test(expected = IllegalArgumentException.class)
	public void acceptOffer_ContractWasAlreadyAccepted_ShouldThrowIllegalArgumentException()
			throws NotFoundRecordException {
		String contractId = "4019";
		service.acceptOffer(contractId);
	}

	@Test
	public void acceptOffer_ContractIsValid_ContractStatusIsAccepted() throws NotFoundRecordException {
		String contractId = "4019";
		service.acceptOffer(contractId);

		Iterator<Contract> resIter = service.getPendingOffers().iterator();

		Contract result = null;

		while (resIter.hasNext()) {
			result = resIter.next();
			assertTrue(result.getStatus() == ContractStatus.PENDING || result.getStatus() == ContractStatus.REJECTED);
		}

	}

	@Test(expected = IllegalArgumentException.class)
	public void rejectOffer_ContractIdIsNull_ShouldThrowIllegalArgumentException() throws NotFoundRecordException {
		service.rejectOffer(null);
	}

	@Test(expected = NotFoundRecordException.class)
	public void rejectOffer_ContractIsNotInTheSystem_ShouldThrowNotFoundRecordException()
			throws NotFoundRecordException {
		String contractId = "11234";
		service.rejectOffer(contractId);
	}

	@Test(expected = IllegalArgumentException.class)
	public void rejectOffer_ContractWasAlreadyAccepted_ShouldThrowIllegalArgumentException()
			throws NotFoundRecordException {
		String contractId = "4019";
		service.rejectOffer(contractId);
	}

	@Test
	public void rejectOffer_ContractIsValid_ContractStatusIsREJECTED() throws NotFoundRecordException {
		String contractId = "923c";
		service.rejectOffer(contractId);

		assertTrue(true); // If we get here nothing went wrong

	}

	@Test
	public void getPendingOffers_ShouldReturnContractsWithStatusPENDING() {
		Set<Contract> contracts = service.getPendingOffers();

		Iterator<Contract> noAccepted = contracts.iterator();

		while (noAccepted.hasNext()) {
			Contract contract = noAccepted.next();

			assertEquals(ContractStatus.PENDING, contract.getStatus());

		}
	}

	// 4019 vin: 3333333 accepted: ACCEPTED
	@Test
	public void getAllPayments_ShouldReturnSetWithPayments() {

		Set<Payment> payments = service.getAllPayments();

		assertEquals(15, payments.size());
	}

	@Test(expected = IllegalArgumentException.class)
	public void getRemainingPayments_ContractIdIsNull_ShouldThrowIllegalArgumentException() throws NotFoundRecordException {
		service.getRemainingPayments(null);
	}

	@Test
	public void getRemainingPayments_ShouldReturnSetWithPayments() throws NotFoundRecordException {

		Set<Payment> payments = service.getRemainingPayments("4019");

		assertEquals(36, payments.size());
	}

	@Test(expected = IllegalArgumentException.class)
	public void getAllPaymentsForCustomer_CustomerUsernameIsNull_ShouldThrowIllegalArgumentException()
			throws NotFoundRecordException {
		service.getAllPaymentsForCustomer(null);
	}

	@Test(expected = NotFoundRecordException.class)
	public void getAllPaymentsForCustomer_CustomerIsNotInFile_ShouldThrowNotFoundRecordException() throws NotFoundRecordException {

		service.getAllPaymentsForCustomer("user22");
	}

	// user2
	@Test
	public void getAllPaymentsForCustomer_CustomerUsernameIsValid_ShouldReturnSetWithPayments()
			throws NotFoundRecordException {

		Set<Payment> payments = service.getAllPaymentsForCustomer("user2");

		assertEquals(15, payments.size());
	}

}
