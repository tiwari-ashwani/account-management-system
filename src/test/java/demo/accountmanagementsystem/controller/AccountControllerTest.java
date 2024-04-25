package demo.accountmanagementsystem.controller;

import static demo.accountmanagementsystem.helper.HelperUtil.readResourceAsAccountRequest;
import static demo.accountmanagementsystem.helper.HelperUtil.readResourceAsAccountResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import demo.accountmanagementsystem.service.AccountService;

public class AccountControllerTest {

	@Test
	public void testGetAccountDetails() throws Exception {
		var accountService = Mockito.mock(AccountService.class);
		Mockito.when(accountService.getAccountDetails()).thenThrow(new RuntimeException("Something went wrong"));
		var subjectUnderTest = new AccountController(accountService);

		var message = assertThrows(RuntimeException.class, () -> {
			subjectUnderTest.getAccountDetails();
		});

		assertEquals("Something went wrong", message.getMessage());

	}

	@Test
	public void testGetAccountById() throws Exception {
		var accountId = UUID.randomUUID();
		var accountService = Mockito.mock(AccountService.class);
		Mockito.when(accountService.getAccountDetailById(accountId))
				.thenThrow(new RuntimeException("Something went wrong"));
		var subjectUnderTest = new AccountController(accountService);

		var message = assertThrows(RuntimeException.class, () -> {
			subjectUnderTest.getAccountById(accountId);
		});

		assertEquals("Something went wrong", message.getMessage());
	}

	@Test
	public void testCreateAccount() throws Exception {
		var accountRequest = readResourceAsAccountRequest("account-request.json");
		var accountResponse = readResourceAsAccountResponse("account-response.json");

		var accountService = Mockito.mock(AccountService.class);
		Mockito.when(accountService.createAccount(accountRequest)).thenReturn(accountResponse);
		var subjectUnderTest = new AccountController(accountService);
		var response = subjectUnderTest.createAccount(accountRequest);

		assertNotNull(response);
	}

	@Test
	public void testCreateAccountshouldReturnInternalServerError() throws Exception {
		var accountRequest = readResourceAsAccountRequest("account-request.json");
		var accountService = Mockito.mock(AccountService.class);
		Mockito.when(accountService.createAccount(accountRequest))
				.thenThrow(new RuntimeException("Something went wrong"));
		var subjectUnderTest = new AccountController(accountService);

		var message = assertThrows(RuntimeException.class, () -> {
			subjectUnderTest.createAccount(accountRequest);
		});

		assertEquals("Something went wrong", message.getMessage());
	}

}