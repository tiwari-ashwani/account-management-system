package demo.accountmanagementsystem;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import com.fasterxml.jackson.core.type.TypeReference;
import com.openapi.gen.springboot.dto.AccountDetails;
import com.openapi.gen.springboot.dto.CreateAccountRequest;
import com.openapi.gen.springboot.dto.CreateAccountResponse;
import com.openapi.gen.springboot.dto.PaymentTransferRequest;
import com.openapi.gen.springboot.dto.PaymentTransferResponse;

import demo.accountmanagementsystem.utils.SerializationHelper;

	@SpringBootTest
	@AutoConfigureMockMvc
	@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
	public abstract class IntegrationTest {
	    
	   

	    @Autowired
	    protected MockMvc mvc;

	    protected CreateAccountResponse createAccount(CreateAccountRequest request) throws Exception {
	        var body = SerializationHelper.toJson(request);
	        assertNotNull(body);
	        var response = mvc
	                .perform(MockMvcRequestBuilders.post("/api/v1/accounts")
	                        .content(body).contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isCreated()).andReturn();
	        var responseBody = response.getResponse().getContentAsString();
	        return SerializationHelper.toObject(responseBody, CreateAccountResponse.class);
	    }



	    protected List<AccountDetails> getAccountDetails() throws Exception {
	        var response = mvc
	                .perform(MockMvcRequestBuilders.get("/api/v1/accounts")
	                        .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn();

	        return SerializationHelper.byTypeReference(response.getResponse().getContentAsString(), new TypeReference<>() {
	        });
	    }

	    protected AccountDetails getAccountById(UUID accountId) throws Exception {
	        var response = mvc
	                .perform(MockMvcRequestBuilders.get("/api/v1/accounts/%s".formatted(accountId))
	                        .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn();

	        return SerializationHelper.byTypeReference(response.getResponse().getContentAsString(), new TypeReference<>() {
	        });
	    }
	    
	    protected PaymentTransferResponse executePaymentTransfer(PaymentTransferRequest request) throws Exception {
	        var body = SerializationHelper.toJson(request);
	        assertNotNull(body);
	        var response = mvc
	                .perform(MockMvcRequestBuilders.post("/api/v1/transactions")
	                        .content(body).contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isCreated()).andReturn();
	        var responseBody = response.getResponse().getContentAsString();
	        return SerializationHelper.toObject(responseBody, PaymentTransferResponse.class);
	    }




}
