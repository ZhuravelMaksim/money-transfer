package io.test.money_transfer.transaction;

import io.test.money_transfer.account.Account;
import io.test.money_transfer.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serial;

@Getter
@Setter
@Entity
@Table(name = "transaction")
@Builder
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class Transaction extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 4169771382047449764L;

    @ManyToOne
    @JoinColumn(name = "account_from_id", updatable = false)
    private Account accountFrom;

    @ManyToOne
    @JoinColumn(name = "account_to_id", updatable = false)
    private Account accountTo;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
}
