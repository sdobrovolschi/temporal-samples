package com.example.transfer.infrastructure.temporal;

import com.example.transfer.application.TransactionDetails;
import com.example.transfer.application.TransferDetails;
import io.temporal.activity.ActivityOptions;
import io.temporal.failure.TemporalFailure;
import io.temporal.spring.boot.WorkflowImpl;
import io.temporal.workflow.Saga;
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
        var saga = new Saga(new Saga.Options.Builder().build());
        log.info("!!!!!!!!!!!! workflow started");

        try {
            var withdrawal = new TransactionDetails(transfer.fromAccountId(), transfer.amount());

            log.info("!!!!!!!!!!!! activity started");
            accountActivities.withdrawMoney(withdrawal);
            log.info("!!!!!!!!!!!! activity completed");

            var deposit = new TransactionDetails(transfer.toAccountId(), transfer.amount());
            saga.addCompensation(accountActivities::depositMoney, withdrawal);
            log.info("!!!!!!!!!!!! activity started");
            accountActivities.depositMoney(deposit);
            log.info("!!!!!!!!!!!! activity completed");

            log.info("!!!!!!!!!!!! workflow completed");
        } catch (TemporalFailure e) {
            saga.compensate();
            throw e;
        }
    }
}
