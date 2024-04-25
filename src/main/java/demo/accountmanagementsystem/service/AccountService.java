package demo.accountmanagementsystem.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.openapi.gen.springboot.dto.AccountDetails;
import com.openapi.gen.springboot.dto.CreateAccountRequest;
import com.openapi.gen.springboot.dto.CreateAccountResponse;
import com.openapi.gen.springboot.dto.CreateAccountResponse.AccountStatusEnum;
import com.openapi.gen.springboot.dto.CreateAccountResponse.AccountTypeEnum;

import demo.accountmanagementsystem.entity.Account;
import demo.accountmanagementsystem.exception.AccountNotFoundException;
import demo.accountmanagementsystem.repository.AccountRepository;
import demo.accountmanagementsystem.utils.ApplicationUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountService {

	private final AccountRepository accountRepository;

	public List<AccountDetails> getAccountDetails() {

		var accountDetails = accountRepository.findAll();
		return StreamSupport.stream(accountDetails.spliterator(), false).map(this::createAccountDetails)
				.collect(Collectors.toList());

	}

	public AccountDetails getAccountDetailById(UUID accountId) {

		return accountRepository.findById(accountId).map(account -> {
			return createAccountDetails(account);
		}).orElseThrow(() -> {
			log.error("Account Details with the id not found {}", accountId);
			throw new AccountNotFoundException("Account Details not found");
		});

	}

	public CreateAccountResponse createAccount(CreateAccountRequest request) {
		var account = accountRepository.save(Account.builder().iban(ApplicationUtility.generateUniqueIban())
				.firstName(request.getFirstName()).lastName(request.getLastName()).emailId(request.getEmailId())
				.accountType(AccountTypeEnum.SAVINGS).accountStatus(AccountStatusEnum.ACTIVE)
				.currentBalance(request.getInitialAmount()).contactNumber(request.getContactNumber()).build());

		return createAccountResponse(account);
	}

	private CreateAccountResponse createAccountResponse(Account account) {
		var accountDetails = new CreateAccountResponse();
		accountDetails.setId(account.id());
		accountDetails.setIban(account.iban());
		accountDetails.setFirstName(account.firstName());
		accountDetails.setLastName(account.lastName());
		accountDetails.setEmailId(account.emailId());
		accountDetails.setCurrentBalance(account.currentBalance());
		accountDetails.setAccountType(account.accountType());
		accountDetails.setAccountStatus(account.accountStatus());
		accountDetails.setContactNumber(account.contactNumber());
		return accountDetails;
	}

	private AccountDetails createAccountDetails(Account account) {
		var accountDetails = new AccountDetails();
		accountDetails.setId(account.id());
		accountDetails.setIban(account.iban());
		accountDetails.setFirstName(account.firstName());
		accountDetails.setLastName(account.lastName());
		accountDetails.setEmailId(account.emailId());
		accountDetails.setCurrentBalance(account.currentBalance());
		accountDetails.setAccountType(account.accountType().getValue());
		accountDetails.setAccountStatus(account.accountStatus().getValue());
		accountDetails.setContactNumber(account.contactNumber());
		return accountDetails;
	}

}
