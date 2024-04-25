package demo.accountmanagementsystem.controller;


import static demo.accountmanagementsystem.helper.HelperUtil.readResourceAsAccountRequest;
import static demo.accountmanagementsystem.helper.HelperUtil.readResourceAsTransferRequest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.stream.StreamSupport;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import demo.accountmanagementsystem.IntegrationTest;
import demo.accountmanagementsystem.repository.TransactionRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TransactionControllerITTest  extends IntegrationTest{
	

	    @Autowired
	    private TransactionRepository transRepo;

	  

	    @BeforeEach
	    public void cleanUp() {
	        System.out.println("cleaning....");

	        StreamSupport.stream(transRepo.findAll().spliterator(), true).forEach(trans -> transRepo.delete(trans));
	    }
//
//	    @Test
//	    public void testExecutePaymentTransfer() throws Exception {
//	        assertNotNull(transRepo);
//	        var accountRequest = readResourceAsAccountRequest("account-request.json");
//	        var accountResponse = createAccount(accountRequest);
//	        var accountResponse1 = createAccount(accountRequest);
//	        var transferRequest = readResourceAsTransferRequest("transfer-request.json");
//	        transferRequest.setPayeeIban(accountResponse.getIban());
//	        transferRequest.setPayerIban(accountResponse1.getIban());
//	        var transferResponse = executePaymentTransfer(transferRequest);
//	        assertNotNull(transferResponse.getTransactionId());
//	    }

	   

}