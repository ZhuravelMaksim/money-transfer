package io.test.money_transfer.account;


import io.test.money_transfer.ApplicationIT;
import io.test.money_transfer.config.validation.ErrorResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AccountControllerIT extends ApplicationIT {

    @Autowired
    private AccountService accountService;

    @Test
    void accountController_createNewAccount_ShouldPass() throws Exception {
        var response = mvc.perform(post("/account"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsByteArray();
        var accountResponse = toModel(response, AccountResponse.class);

        Assertions.assertEquals(accountResponse.getBalance(), 0);
    }

    @Test
    void accountController_updateBalanceForExistingAccount_ShouldPass() throws Exception {
        var account = accountService.create();
        var request = new UpdateBalanceRequest();
        request.setId(account.getId());
        request.setAmount(100.0);

        var response = mvc.perform(post("/account/updateBalance")
                .contentType(APPLICATION_JSON)
                .content(toJson(request)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsByteArray();
        var accountResponse = toModel(response, AccountResponse.class);

        Assertions.assertEquals(accountResponse.getBalance(), 100.0);
    }

    @Test
    void accountController_updateBalanceForNonExistingAccount_ShouldFail() throws Exception {
        var request = new UpdateBalanceRequest();
        request.setId("1111-11111-1111-1111");
        request.setAmount(100.0);

        var response = mvc.perform(post("/account/updateBalance")
                .contentType(APPLICATION_JSON)
                .content(toJson(request)))
                .andExpect(status().is(404))
                .andReturn().getResponse().getContentAsByteArray();
        var errorResponse = toModel(response, ErrorResponse.class);
        assertEquals("Not Found", errorResponse.getStatus());
    }
}
