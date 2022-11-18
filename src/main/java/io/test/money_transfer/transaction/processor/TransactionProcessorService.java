package io.test.money_transfer.transaction.processor;

import io.test.money_transfer.account.AccountService;
import io.test.money_transfer.config.validation.ValidationException;
import io.test.money_transfer.transaction.Transaction;
import io.test.money_transfer.transaction.TransactionService;
import io.test.money_transfer.transaction.TransactionStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionProcessorService {

    private final TransactionService transactionService;
    private final AccountService accountService;


    public Transaction process(final String accountFromId, final String accountToId, final Double amount) {
        var accountFrom = accountService.withdraw(accountFromId, amount);
        return transactionService.create(accountFrom, accountService.getById(accountToId), amount);
    }

    public Transaction terminate(final TransactionStatus transactionStatus, final String transactionId) {
        var transaction = transactionService.getById(transactionId);
        if (TransactionStatus.terminatedStatuses().contains(transaction.getStatus())) {
            throw new ValidationException("TRANSACTION_ALREADY_SUBMITTED");
        }
        var amount = transaction.getAmount();
        if (TransactionStatus.APPROVED == transactionStatus) {
            var accountToId = transaction.getAccountTo().getId();
            accountService.updateBalance(accountToId, amount);
        } else {
            var accountFromId = transaction.getAccountFrom().getId();
            accountService.updateBalance(accountFromId, amount);
        }
        return transactionService.updateStatus(transactionId, transactionStatus);
    }
}
