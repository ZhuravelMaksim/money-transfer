package io.test.money_transfer.transaction;


import io.test.money_transfer.account.AccountResponse;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TransactionResponse {

    private String id;

    private AccountResponse accountFrom;

    private AccountResponse accountTo;

    private Double amount;

    private TransactionStatus status;
}
