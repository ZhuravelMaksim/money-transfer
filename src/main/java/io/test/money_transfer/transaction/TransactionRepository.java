package io.test.money_transfer.transaction;

import org.springframework.data.jpa.repository.JpaRepository;


public interface TransactionRepository extends JpaRepository<Transaction, String> {


}
