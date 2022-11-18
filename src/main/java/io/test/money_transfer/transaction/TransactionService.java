package io.test.money_transfer.transaction;

import io.test.money_transfer.account.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository repository;

    @Transactional
    public Transaction create(final Account accountFrom, final Account accountTo, final Double amount) {
        var transaction = Transaction.of(accountFrom, accountTo, amount, TransactionStatus.PROCESSING);
        return repository.save(transaction);
    }

    @Transactional
    public Transaction updateStatus(final String id, final TransactionStatus status) {
        return repository.findById(id)
                .map(account -> {
                    account.setStatus(status);
                    return account;
                })
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<Transaction> getAll() {
        return repository.findAll();
    }

    public Transaction getById(final String id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

}
