package io.test.money_transfer.transaction;


import io.test.money_transfer.RepositoryIT;
import io.test.money_transfer.account.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class TransactionRepositoryIT extends RepositoryIT {

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    void findAllTransactions_ShouldReturnEmptyList() {
        var accounts = transactionRepository.findAll();
        assertEquals(0, accounts.size());
    }

}
