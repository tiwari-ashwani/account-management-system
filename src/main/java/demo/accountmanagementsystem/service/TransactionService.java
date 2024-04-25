package demo.accountmanagementsystem.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.openapi.gen.springboot.dto.CreateAccountResponse.AccountStatusEnum;
import com.openapi.gen.springboot.dto.PaymentTransferRequest;
import com.openapi.gen.springboot.dto.PaymentTransferResponse;

import demo.accountmanagementsystem.entity.Account;
import demo.accountmanagementsystem.entity.AccountTransaction;
import demo.accountmanagementsystem.exception.AccountNotFoundException;
import demo.accountmanagementsystem.exception.InsufficientBalanceException;
import demo.accountmanagementsystem.exception.PaymentTransferFailureException;
import demo.accountmanagementsystem.repository.AccountRepository;
import demo.accountmanagementsystem.repository.TransactionRepository;
import demo.accountmanagementsystem.utils.ApplicationConstants;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class TransactionService {

	private final AccountRepository accountsRepository;

	private final TransactionRepository transactionsRepository;

	public PaymentTransferResponse executePaymentTransfer(PaymentTransferRequest request) {

		var payerAccount = accountsRepository
				.findByIbanAndAccountStatus(request.getPayerIban(), AccountStatusEnum.ACTIVE)
				.orElseThrow(() -> new AccountNotFoundException(null));
		var payeeAccount = accountsRepository
				.findByIbanAndAccountStatus(request.getPayeeIban(), AccountStatusEnum.ACTIVE)
				.orElseThrow(() -> new AccountNotFoundException(null));

		validateBalance(payerAccount.currentBalance(), request.getTransferAmount());

		return transfer(payerAccount, payeeAccount, request.getTransferAmount());

	}

	private void validateBalance(BigDecimal currentBalance, BigDecimal transferAmount) {
		log.info("Validating current balance : {}", transferAmount);

		if (currentBalance.compareTo(BigDecimal.ZERO) < 0 || currentBalance.compareTo(transferAmount) < 0) {
			throw new InsufficientBalanceException("Insufficient balance !!");
		}
	}

	public PaymentTransferResponse transfer(Account payerAccount, Account payeeAccount, BigDecimal transferAmount) {
		UUID transactionId = UUID.randomUUID();

		try {
			log.info("Initiating fund transfer between {} {} :", payerAccount, payeeAccount);

			payerAccount.currentBalance(payerAccount.currentBalance().subtract(transferAmount));
			payeeAccount.currentBalance(payeeAccount.currentBalance().add(transferAmount));
			accountsRepository.saveAll(List.of(payerAccount, payeeAccount));

			log.info("Payment transfer executed between {} {} :", payerAccount.iban(), payeeAccount.iban());

			AccountTransaction transaction = transactionsRepository.save(AccountTransaction.builder()
					.transactionStatus(ApplicationConstants.SUCCESS_MESSAGE).transactionId(transactionId)
					.account(payeeAccount).amount(payeeAccount.currentBalance().add(transferAmount)).build());

			log.info("Transaction table updated : ");

			return createPaymentTransferResponse(transaction);
		} catch (Exception ex) {
			transactionsRepository
					.save(AccountTransaction.builder().transactionStatus(ApplicationConstants.FAILURE_MESSAGE)
							.transactionId(transactionId).account(payerAccount).build());
			throw new PaymentTransferFailureException("Something went wrong, unable to transfer funds!!");

		}

	}

	private PaymentTransferResponse createPaymentTransferResponse(AccountTransaction transaction) {
		var transferDetails = new PaymentTransferResponse();
		transferDetails.setTransactionId(transaction.transactionId());
		transferDetails.setTransactionStatus(transaction.transactionStatus());
		transferDetails.setAmount(transaction.amount());
		return transferDetails;
	}

}
