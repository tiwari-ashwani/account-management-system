package demo.accountmanagementsystem.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import demo.accountmanagementsystem.service.TransactionService;
import static demo.accountmanagementsystem.helper.HelperUtil.readResourceAsTransferRequest;
import static demo.accountmanagementsystem.helper.HelperUtil.readResourceAsTransferResponse;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionControllerTest {

    

    @Test
    public void testExecutePaymentTransfer() throws Exception {
    	var transferRequest = readResourceAsTransferRequest("transfer-request.json");
    	var transferResponse = readResourceAsTransferResponse("transfer-response.json");

        var transService = Mockito.mock(TransactionService.class);
        Mockito.when(transService.executePaymentTransfer(transferRequest)).thenReturn(transferResponse);
        var subjectUnderTest = new TransactionController(transService);

        var response =  subjectUnderTest.executePaymentTransfer(transferRequest);
       
        //assertNotNull(response);
    }

    @Test
    public void testExecutePaymentTransferInternalServerError() throws Exception {
    	var transferRequest = readResourceAsTransferRequest("transfer-request.json");
        var transService = Mockito.mock(TransactionService.class);
        Mockito.when(transService.executePaymentTransfer(transferRequest))
                .thenThrow(new RuntimeException("Something went wrong"));
        var subjectUnderTest = new TransactionController(transService);

        var message = assertThrows(RuntimeException.class, () -> {
            subjectUnderTest.executePaymentTransfer(transferRequest);
        });

        assertEquals("Something went wrong", message.getMessage());
    }

}
