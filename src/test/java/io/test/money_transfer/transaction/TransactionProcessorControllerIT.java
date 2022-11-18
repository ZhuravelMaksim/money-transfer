package io.test.money_transfer.transaction;


import io.test.money_transfer.ApplicationIT;
import io.test.money_transfer.account.AccountService;
import io.test.money_transfer.config.validation.ErrorResponse;
import io.test.money_transfer.transaction.processor.TransactionProcessRequest;
import io.test.money_transfer.transaction.processor.TransactionTerminateRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TransactionProcessorControllerIT extends ApplicationIT {

    @Autowired
    private AccountService accountService;

    @Test
    void transactionProcessController_createNewTransactionByExistingAccounts_andTerminateWithApprove_ShouldPass() throws Exception {
        var account1 = accountService.create();
        var account2 = accountService.create();
        accountService.updateBalance(account1.getId(), 100.0);
        accountService.updateBalance(account2.getId(), 200.0);

        var transactionProcessRequest = new TransactionProcessRequest();
        transactionProcessRequest.setAccountFrom(account1.getId());
        transactionProcessRequest.setAccountTo(account2.getId());
        transactionProcessRequest.setAmount(100.0);

        var response1 = mvc.perform(post("/transaction/create")
                .contentType(APPLICATION_JSON)
                .content(toJson(transactionProcessRequest)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsByteArray();
        var transactionProcessResponse = toModel(response1, TransactionResponse.class);

        Assertions.assertEquals(transactionProcessResponse.getAmount(), 100.0);
        Assertions.assertEquals(transactionProcessResponse.getStatus(), TransactionStatus.PROCESSING);
        Assertions.assertEquals(transactionProcessResponse.getAccountFrom().getId(), account1.getId());
        Assertions.assertEquals(transactionProcessResponse.getAccountTo().getId(), account2.getId());
        Assertions.assertEquals(transactionProcessResponse.getAccountFrom().getBalance(), 0.0);
        Assertions.assertEquals(transactionProcessResponse.getAccountTo().getBalance(), 200.0);

        var transactionTerminateRequest = new TransactionTerminateRequest();
        transactionTerminateRequest.setTransactionId(transactionProcessResponse.getId());
        transactionTerminateRequest.setTransactionStatus(TransactionStatus.APPROVED);

        var response2 = mvc.perform(post("/transaction/terminate")
                .contentType(APPLICATION_JSON)
                .content(toJson(transactionTerminateRequest)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsByteArray();
        var transactionTerminateResponse = toModel(response2, TransactionResponse.class);

        Assertions.assertEquals(transactionTerminateResponse.getAmount(), 100.0);
        Assertions.assertEquals(transactionTerminateResponse.getStatus(), TransactionStatus.APPROVED);
        Assertions.assertEquals(transactionTerminateResponse.getAccountFrom().getId(), account1.getId());
        Assertions.assertEquals(transactionTerminateResponse.getAccountTo().getId(), account2.getId());
        Assertions.assertEquals(transactionTerminateResponse.getAccountFrom().getBalance(), 0.0);
        Assertions.assertEquals(transactionTerminateResponse.getAccountTo().getBalance(), 300.0);

    }

    @Test
    void transactionProcessController_createNewTransactionByExistingAccounts_andTerminateWithDecline_ShouldPass() throws Exception {
        var account1 = accountService.create();
        var account2 = accountService.create();
        accountService.updateBalance(account1.getId(), 100.0);
        accountService.updateBalance(account2.getId(), 200.0);

        var transactionProcessRequest = new TransactionProcessRequest();
        transactionProcessRequest.setAccountFrom(account1.getId());
        transactionProcessRequest.setAccountTo(account2.getId());
        transactionProcessRequest.setAmount(100.0);

        var response1 = mvc.perform(post("/transaction/create")
                .contentType(APPLICATION_JSON)
                .content(toJson(transactionProcessRequest)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsByteArray();
        var transactionProcessResponse = toModel(response1, TransactionResponse.class);

        Assertions.assertEquals(transactionProcessResponse.getAmount(), 100.0);
        Assertions.assertEquals(transactionProcessResponse.getStatus(), TransactionStatus.PROCESSING);
        Assertions.assertEquals(transactionProcessResponse.getAccountFrom().getId(), account1.getId());
        Assertions.assertEquals(transactionProcessResponse.getAccountTo().getId(), account2.getId());
        Assertions.assertEquals(transactionProcessResponse.getAccountFrom().getBalance(), 0.0);
        Assertions.assertEquals(transactionProcessResponse.getAccountTo().getBalance(), 200.0);

        var transactionTerminateRequest = new TransactionTerminateRequest();
        transactionTerminateRequest.setTransactionId(transactionProcessResponse.getId());
        transactionTerminateRequest.setTransactionStatus(TransactionStatus.DECLINED);

        var response2 = mvc.perform(post("/transaction/terminate")
                .contentType(APPLICATION_JSON)
                .content(toJson(transactionTerminateRequest)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsByteArray();
        var transactionTerminateResponse = toModel(response2, TransactionResponse.class);

        Assertions.assertEquals(transactionTerminateResponse.getAmount(), 100.0);
        Assertions.assertEquals(transactionTerminateResponse.getStatus(), TransactionStatus.DECLINED);
        Assertions.assertEquals(transactionTerminateResponse.getAccountFrom().getId(), account1.getId());
        Assertions.assertEquals(transactionTerminateResponse.getAccountTo().getId(), account2.getId());
        Assertions.assertEquals(transactionTerminateResponse.getAccountFrom().getBalance(), 100.0);
        Assertions.assertEquals(transactionTerminateResponse.getAccountTo().getBalance(), 200.0);

    }

    @Test
    void transactionProcessController_createNewTransactionByExistingAccounts_andTryTerminateAlreadyTerminated_ShouldFail() throws Exception {
        var account1 = accountService.create();
        var account2 = accountService.create();
        accountService.updateBalance(account1.getId(), 100.0);
        accountService.updateBalance(account2.getId(), 200.0);

        var transactionProcessRequest = new TransactionProcessRequest();
        transactionProcessRequest.setAccountFrom(account1.getId());
        transactionProcessRequest.setAccountTo(account2.getId());
        transactionProcessRequest.setAmount(100.0);

        var response1 = mvc.perform(post("/transaction/create")
                .contentType(APPLICATION_JSON)
                .content(toJson(transactionProcessRequest)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsByteArray();
        var transactionProcessResponse = toModel(response1, TransactionResponse.class);

        Assertions.assertEquals(transactionProcessResponse.getAmount(), 100.0);
        Assertions.assertEquals(transactionProcessResponse.getStatus(), TransactionStatus.PROCESSING);
        Assertions.assertEquals(transactionProcessResponse.getAccountFrom().getId(), account1.getId());
        Assertions.assertEquals(transactionProcessResponse.getAccountTo().getId(), account2.getId());
        Assertions.assertEquals(transactionProcessResponse.getAccountFrom().getBalance(), 0.0);
        Assertions.assertEquals(transactionProcessResponse.getAccountTo().getBalance(), 200.0);

        var transactionTerminateRequest = new TransactionTerminateRequest();
        transactionTerminateRequest.setTransactionId(transactionProcessResponse.getId());
        transactionTerminateRequest.setTransactionStatus(TransactionStatus.DECLINED);

        var response2 = mvc.perform(post("/transaction/terminate")
                .contentType(APPLICATION_JSON)
                .content(toJson(transactionTerminateRequest)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsByteArray();
        var transactionTerminateResponse = toModel(response2, TransactionResponse.class);

        Assertions.assertEquals(transactionTerminateResponse.getAmount(), 100.0);
        Assertions.assertEquals(transactionTerminateResponse.getStatus(), TransactionStatus.DECLINED);
        Assertions.assertEquals(transactionTerminateResponse.getAccountFrom().getId(), account1.getId());
        Assertions.assertEquals(transactionTerminateResponse.getAccountTo().getId(), account2.getId());
        Assertions.assertEquals(transactionTerminateResponse.getAccountFrom().getBalance(), 100.0);
        Assertions.assertEquals(transactionTerminateResponse.getAccountTo().getBalance(), 200.0);

        var response3 = mvc.perform(post("/transaction/terminate")
                .contentType(APPLICATION_JSON)
                .content(toJson(transactionTerminateRequest)))
                .andExpect(status().is(400))
                .andReturn().getResponse().getContentAsByteArray();
        var errorResponse = toModel(response3, ErrorResponse.class);
        assertEquals("BAD_REQUEST", errorResponse.getStatus());
        assertEquals("TRANSACTION_ALREADY_SUBMITTED", errorResponse.getMessage());
    }

    @Test
    void transactionProcessController_createNewTransactionByNonExistingAccounts_ShouldFail() throws Exception {
        var request = new TransactionProcessRequest();
        request.setAccountFrom("11111-1111-11111-1111");
        request.setAccountTo("22222-2222-22222-2222");
        request.setAmount(200.0);

        var response = mvc.perform(post("/transaction/create")
                .contentType(APPLICATION_JSON)
                .content(toJson(request)))
                .andExpect(status().is(404))
                .andReturn().getResponse().getContentAsByteArray();
        var errorResponse = toModel(response, ErrorResponse.class);
        assertEquals("Not Found", errorResponse.getStatus());
    }

    @Test
    void transactionProcessController_createNewTransactionByExistingAccountsWithoutEnoughBalance_ShouldFail() throws Exception {
        var account1 = accountService.create();
        var account2 = accountService.create();
        accountService.updateBalance(account1.getId(), 100.0);
        accountService.updateBalance(account2.getId(), 200.0);

        var request = new TransactionProcessRequest();
        request.setAccountFrom(account1.getId());
        request.setAccountTo(account2.getId());
        request.setAmount(200.0);

        var response = mvc.perform(post("/transaction/create")
                .contentType(APPLICATION_JSON)
                .content(toJson(request)))
                .andExpect(status().is(400))
                .andReturn().getResponse().getContentAsByteArray();
        var errorResponse = toModel(response, ErrorResponse.class);
        assertEquals("BAD_REQUEST", errorResponse.getStatus());
        assertEquals("INVALID_BALANCE", errorResponse.getMessage());
    }
}
