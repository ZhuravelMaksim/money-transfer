package io.test.money_transfer.account;


import io.test.money_transfer.RepositoryIT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class AccountRepositoryIT extends RepositoryIT {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void findAllAccounts_ShouldReturnEmptyList() {
        var accounts = accountRepository.findAll();
        assertEquals(0, accounts.size());
    }

}
