package io.test.money_transfer.account;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class UpdateBalanceRequest {
    @NotBlank
    private String id;
    @Positive
    private Double amount;
}
