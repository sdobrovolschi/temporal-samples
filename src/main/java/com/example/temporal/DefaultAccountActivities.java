package com.example.temporal;

import com.example.temporal.AccountsApi.TransactionRequest;
import io.temporal.spring.boot.ActivityImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.example.temporal.AccountsApi.TransactionRequest.Type.DEPOSIT;
import static com.example.temporal.AccountsApi.TransactionRequest.Type.WITHDRAWAL;

@Component
@ActivityImpl(taskQueues = TaskQueue.NAME)
@Slf4j
@RequiredArgsConstructor
public class DefaultAccountActivities implements AccountActivities {

    private final AccountsApi accountsApi;

    // https://docs.temporal.io/activity-definition#idempotency
    // https://docs.temporal.io/references/commands#scheduleactivitytask
    @Override
    public void depositMoney(TransactionDetails transaction) {
        log.info("account deposit");
        var request = new TransactionRequest(DEPOSIT, transaction.amount());
        accountsApi.depositMoney(transaction.accountId(), request);
    }

    // https://docs.temporal.io/activity-definition#idempotency
    // https://docs.temporal.io/references/commands#scheduleactivitytask
    @Override
    public void withdrawMoney(TransactionDetails transaction) {
        log.info("account withdrawal");
        var request = new TransactionRequest(WITHDRAWAL, transaction.amount());
        accountsApi.withdrawMoney(transaction.accountId(), request);
    }
}
