package demo.accountmanagementsystem.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.openapi.gen.springboot.api.TransactionsApi;
import com.openapi.gen.springboot.dto.PaymentTransferRequest;
import com.openapi.gen.springboot.dto.PaymentTransferResponse;

import demo.accountmanagementsystem.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
@Slf4j
public class TransactionController implements TransactionsApi {

	private final TransactionService transactionService;

	@Override
	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(CREATED)
	public ResponseEntity<PaymentTransferResponse> executePaymentTransfer(@RequestBody PaymentTransferRequest request) {
		PaymentTransferResponse response = null;
		try 
		{
			response = transactionService.executePaymentTransfer(request);
			return null;
		} 
		catch (DataIntegrityViolationException | IllegalArgumentException ex) 
		{
			log.warn("Error during customer account creation: {}", ex.getMessage());
			return null;
		}
	}

}