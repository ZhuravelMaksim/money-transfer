package io.test.money_transfer.account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountResponse {

    private String id;

    private String accountNumber;

    private Double balance;
}
