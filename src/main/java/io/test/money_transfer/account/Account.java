package io.test.money_transfer.account;

import io.test.money_transfer.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serial;

@Getter
@Setter
@Entity
@Table(name = "account")
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class Account extends BaseEntity {

    @Serial
    private static final long serialVersionUID = -3467996453730456986L;

    private String accountNumber;

    private Double balance;
}
