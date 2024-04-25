package demo.accountmanagementsystem.controller;

import static demo.accountmanagementsystem.helper.HelperUtil.readResourceAsAccountRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;
import java.util.stream.StreamSupport;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import demo.accountmanagementsystem.IntegrationTest;
import demo.accountmanagementsystem.repository.AccountRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AccountControllerITTest extends IntegrationTest {

	@Autowired
	private AccountRepository accountRepo;

	@BeforeEach
	public void cleanUp() {
		System.out.println("cleaning....");
		StreamSupport.stream(accountRepo.findAll().spliterator(), true).forEach(account -> accountRepo.delete(account));
	}

	@Test
	public void testCreateAccount() throws Exception {
		assertNotNull(accountRepo);
		var accountRequest = readResourceAsAccountRequest("account-request.json");
		var accountResponse = createAccount(accountRequest);
		assertNotNull(accountResponse.getIban());
	}

	@Test
	public void testGetAccountById() throws Exception {
		assertNotNull(accountRepo);

		var response = getAccountById(UUID.randomUUID());
		assertNotNull(response.getId());
	}

	@Test
	void testGetAccountDetails() throws Exception {
		var accounts = Lists.newArrayList(accountRepo.findAll());
		assertEquals(0, accounts.size());

		var accountRequest = readResourceAsAccountRequest("account-request.json");
		var accountResponse = createAccount(accountRequest);

		var accountRequest1 = readResourceAsAccountRequest("account-request.json");
		var accountResponse1 = createAccount(accountRequest1);

		var accountsLt = Lists.newArrayList(accountRepo.findAll());

		assertEquals(2, accountsLt.size());
	}

}
