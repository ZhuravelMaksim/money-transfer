package io.test.money_transfer.transaction;

import java.util.List;

public enum TransactionStatus {

    PROCESSING,
    APPROVED,
    DECLINED,
    TIMEOUT,
    ERROR;

    public static List<TransactionStatus> processingStatuses() {
        return List.of(PROCESSING);
    }

    public static List<TransactionStatus> terminatedStatuses() {
        return List.of(TIMEOUT, DECLINED, ERROR, APPROVED);
    }
}
