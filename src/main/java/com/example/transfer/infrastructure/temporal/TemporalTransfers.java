package com.example.transfer.infrastructure.temporal;

import com.example.transfer.lang.Money;
import com.example.transfer.application.TransferDetails;
import com.example.transfer.application.Transfers;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Currency;
import java.util.UUID;

import static io.temporal.api.enums.v1.WorkflowIdConflictPolicy.WORKFLOW_ID_CONFLICT_POLICY_FAIL;
import static io.temporal.api.enums.v1.WorkflowIdReusePolicy.WORKFLOW_ID_REUSE_POLICY_REJECT_DUPLICATE;
import static java.util.UUID.randomUUID;

@Component
@RequiredArgsConstructor
public final class TemporalTransfers implements Transfers {

    private final WorkflowClient client;

    @Override
    public void add(UUID transferId, TransferDetails transfer) {
        var workflow = newWorkflow(transferId);

        var transferDetails = new TransferDetails(
                randomUUID(),
                randomUUID(),
                new Money(BigDecimal.valueOf(100), Currency.getInstance("USD"))
        );

        // https://docs.temporal.io/develop/java/temporal-clients#start-workflow-execution
        WorkflowClient.start(workflow::initiateTransfer, transferDetails);
    }

    private MoneyTransferWorkflow newWorkflow(UUID transferId) {
        return client.newWorkflowStub(MoneyTransferWorkflow.class,
                WorkflowOptions.newBuilder()
                        .setTaskQueue(TaskQueue.NAME)
                        .setWorkflowId(transferId.toString())
                        .setWorkflowIdReusePolicy(WORKFLOW_ID_REUSE_POLICY_REJECT_DUPLICATE)
                        .setWorkflowIdConflictPolicy(WORKFLOW_ID_CONFLICT_POLICY_FAIL)
                        // This timeout is primarily available to recognize whether a Worker has gone down so that the Workflow Execution can be recovered on a different Worker.
                        // https://docs.temporal.io/encyclopedia/detecting-workflow-failures#workflow-task-timeout
                        .setWorkflowTaskTimeout(Duration.ofSeconds(5))
                        .build());
    }
}
