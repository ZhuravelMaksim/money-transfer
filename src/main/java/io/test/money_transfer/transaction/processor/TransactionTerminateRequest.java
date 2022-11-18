package io.test.money_transfer.transaction.processor;

import io.test.money_transfer.transaction.TransactionStatus;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TransactionTerminateRequest {
    @NotNull
    private TransactionStatus transactionStatus;
    @NotBlank
    private String transactionId;
}
