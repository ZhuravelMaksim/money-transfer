package io.test.money_transfer.transaction.processor;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class TransactionProcessRequest {
    @NotBlank
    private String accountFrom;
    @NotBlank
    private String accountTo;
    @Positive
    private Double amount;
}
