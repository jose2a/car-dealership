package com.revature.cardealership.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.revature.cardealership.exceptions.NotFoundRecordException;
import com.revature.cardealership.exceptions.PreexistingRecordException;
import com.revature.cardealership.model.User;

public class UserServiceImplTest {

	private UserServiceImpl userService;

	@Before
	public void setUp() throws Exception {
		userService = new UserServiceImpl();
	}

	@Test(expected = IllegalArgumentException.class)
	public void loginUserIsNullShouldThrowIllegalArgumentException() throws NotFoundRecordException {
		userService.login(null, "s3cret");
	}

	@Test(expected = IllegalArgumentException.class)
	public void loginPasswordIsNullShouldThrowIllegalArgumentException() throws NotFoundRecordException {
		userService.login("user1", null);
	}

	@Test
	public void loginUsernameIsNotValidShouldReturnNull() throws NotFoundRecordException {
		User user = userService.login("user", "s3cret");

		assertEquals(null, user);
	}

	@Test
	public void loginPasswordIsNotValidShouldReturnNull() throws NotFoundRecordException {
		User user = userService.login("user1", "scret");

		assertEquals(null, user);
	}
	
	@Test
	public void loginUsernameAndPasswordAreValidShouldReturnUser() throws NotFoundRecordException {
		User user = userService.login("user1", "s3cret");

		assertEquals("John", user.getFirstName());
	}

	@Test(expected = IllegalArgumentException.class)
	public void registerUsernameIsNullShouldThrowIllegalArgumentException() throws PreexistingRecordException {
		userService.registerCustomer(null, "s3cret", "Jane", "Doe");
	}

	@Test(expected = PreexistingRecordException.class)
	public void registerUsernameUserInFileAlreadyhouldThrowPreexistingRecordException()
			throws PreexistingRecordException {
		userService.registerCustomer("user1", "s3cret", "Jane", "Doe");
	}

	@Test
	public void registerUsernameUserIsValidShouldReturnTrue() throws PreexistingRecordException {
		boolean registered = userService.registerCustomer("user2", "s3cret", "Jane", "Doe");

		assertTrue(registered);
	}

}
