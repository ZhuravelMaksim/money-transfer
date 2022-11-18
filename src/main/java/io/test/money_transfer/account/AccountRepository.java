package io.test.money_transfer.account;

import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountRepository extends JpaRepository<Account, String> {


}
