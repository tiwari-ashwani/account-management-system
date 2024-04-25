package demo.accountmanagementsystem.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;
import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.openapi.gen.springboot.api.AccountsApi;
import com.openapi.gen.springboot.dto.AccountDetails;
import com.openapi.gen.springboot.dto.CreateAccountRequest;
import com.openapi.gen.springboot.dto.CreateAccountResponse;

import demo.accountmanagementsystem.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
@Slf4j
public class AccountController implements AccountsApi {

	private final AccountService accountService;

	@Override
	@GetMapping(produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AccountDetails>> getAccountDetails() {
		log.info("Retrieving List of the all the Account Holders ");
		return new ResponseEntity<List<AccountDetails>>(accountService.getAccountDetails(), HttpStatus.OK);
	}
	
	@Override
    @GetMapping(path = "/{accountId}", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<AccountDetails> getAccountById(@PathVariable(required = true) UUID accountId) {
		log.info("Fetching Account Details for the account ID {} : ", accountId );
		return new ResponseEntity<AccountDetails>(accountService.getAccountDetailById(accountId), HttpStatus.OK);
	}

	@Override
	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(CREATED)
	public ResponseEntity<CreateAccountResponse> createAccount(@RequestBody CreateAccountRequest request) {
		CreateAccountResponse response = null;
		try 
		{
			response = accountService.createAccount(request);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} 
		catch (DataIntegrityViolationException | IllegalArgumentException ex) 
		{
			log.warn("Error during customer account creation: {}", ex.getMessage());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

}