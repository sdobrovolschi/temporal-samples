package com.example.temporal;

import io.temporal.activity.ActivityOptions;
import io.temporal.spring.boot.WorkflowImpl;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@WorkflowImpl(taskQueues = TaskQueue.NAME)
@Slf4j
public class DefaultMoneyTransferWorkflow implements MoneyTransferWorkflow {

    private final AccountActivities accountActivities =
            io.temporal.workflow.Workflow.newActivityStub(
                    AccountActivities.class,
                    ActivityOptions.newBuilder()
                            .setTaskQueue(TaskQueue.NAME)
                            // The Temporal Server doesn't detect failures when a Worker loses communication with the Server or crashes.
                            // Therefore, the Temporal Server relies on the Start-To-Close Timeout to force Activity retries.
                            // https://docs.temporal.io/encyclopedia/detecting-activity-failures#start-to-close-timeout
                            .setStartToCloseTimeout(Duration.ofSeconds(2))
                            .build());

    @Override
    public void initiateTransfer(TransferDetails transfer) {
        log.info("!!!!!!!!!!!! workflow started");

        var withdrawal = new TransactionDetails(transfer.fromAccountId(), transfer.amount());
        accountActivities.withdrawMoney(withdrawal);

        var deposit = new TransactionDetails(transfer.toAccountId(), transfer.amount());
        accountActivities.depositMoney(deposit);

        log.info("!!!!!!!!!!!! workflow completed");
    }
}
