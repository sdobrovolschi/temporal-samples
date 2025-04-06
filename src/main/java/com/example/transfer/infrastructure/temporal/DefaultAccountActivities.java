package com.example.transfer.infrastructure.temporal;

import com.example.transfer.application.AccountUseCases;
import com.example.transfer.application.TransactionDetails;
import io.temporal.failure.ApplicationFailure;
import io.temporal.spring.boot.ActivityImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static io.temporal.failure.ApplicationFailure.newNonRetryableFailure;

@Component
@ActivityImpl(taskQueues = TaskQueue.NAME)
@Slf4j
@RequiredArgsConstructor
public class DefaultAccountActivities implements AccountActivities {

    private final AccountUseCases accountUseCases;

    boolean firstCall = true;

    // https://docs.temporal.io/activity-definition#idempotency
    // https://docs.temporal.io/references/commands#scheduleactivitytask
    @Override
    public void depositMoney(TransactionDetails transaction) {
        log.info("!!!!!!!!!!!! account deposit");
//        if (firstCall) {
//            firstCall = false;
//            throw newNonRetryableFailure("Account deposit failed", "AccountDepositFailedDueToBlockedAccount");
//        }

        accountUseCases.depositMoney(transaction);
    }

    // https://docs.temporal.io/activity-definition#idempotency
    // https://docs.temporal.io/references/commands#scheduleactivitytask
    @Override
    public void withdrawMoney(TransactionDetails transaction) {
        log.info("!!!!!!!!!!!! account withdrawal");
        accountUseCases.withdrawMoney(transaction);
    }
}
