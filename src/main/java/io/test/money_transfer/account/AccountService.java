package io.test.money_transfer.account;

import io.test.money_transfer.config.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {

    private final Function<String, RLock> accountBalanceLock;
    private final AccountRepository repository;

    @Transactional
    public Account create() {
        var accountNumber = UUID.randomUUID().toString().replace("-", "");
        var account = Account.of(accountNumber, 0D);
        return repository.save(account);
    }

    @Transactional
    public Account withdraw(final String id, final Double amount) {
        var lock = accountBalanceLock.apply(id);
        lock.lock();
        log.info("Lock acquired for id:" + id);
        try {
            var accountFrom = getById(id);
            var balance = accountFrom.getBalance();
            if (balance < amount) {
                throw new ValidationException("INVALID_BALANCE");
            }
            return updateBalanceInternal(id, -amount);
        } finally {
            lock.unlock();
            log.info("Lock released for id:" + id);
        }
    }

    @Transactional
    public Account updateBalance(final String id, final Double amount) {
        var lock = accountBalanceLock.apply(id);
        lock.lock();
        log.info("Lock acquired for id:" + id);
        try {
            return updateBalanceInternal(id, amount);
        } finally {
            lock.unlock();
            log.info("Lock released for id:" + id);
        }
    }

    private Account updateBalanceInternal(final String id, final Double amount) {
        return repository.findById(id)
                .map(account -> {
                    account.setBalance(account.getBalance() + amount);
                    return account;
                })
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<Account> getAll() {
        return repository.findAll();
    }

    public Account getById(final String id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
