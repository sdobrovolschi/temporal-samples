package com.example.temporal;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Currency;

import static io.temporal.api.enums.v1.WorkflowIdConflictPolicy.WORKFLOW_ID_CONFLICT_POLICY_FAIL;
import static io.temporal.api.enums.v1.WorkflowIdReusePolicy.WORKFLOW_ID_REUSE_POLICY_REJECT_DUPLICATE;
import static java.util.UUID.randomUUID;

@RestController
@RequestMapping(path = "/resources")
@Slf4j
@RequiredArgsConstructor
class Resource {

    private final WorkflowClient client;

    @PostMapping
    String start() {
        var workflowId = randomUUID();

        var workflow = client.newWorkflowStub(MoneyTransferWorkflow.class,
                WorkflowOptions.newBuilder()
                        .setTaskQueue(TaskQueue.NAME)
                        .setWorkflowId(workflowId.toString())
                        .setWorkflowIdReusePolicy(WORKFLOW_ID_REUSE_POLICY_REJECT_DUPLICATE)
                        .setWorkflowIdConflictPolicy(WORKFLOW_ID_CONFLICT_POLICY_FAIL)
                        // This timeout is primarily available to recognize whether a Worker has gone down so that the Workflow Execution can be recovered on a different Worker.
                        // https://docs.temporal.io/encyclopedia/detecting-workflow-failures#workflow-task-timeout
                        .setWorkflowTaskTimeout(Duration.ofSeconds(5))
                        .build());

        var transferDetails = new TransferDetails(
                randomUUID(),
                randomUUID(),
                new Money(BigDecimal.valueOf(100), Currency.getInstance("USD"))
        );

        // https://docs.temporal.io/develop/java/temporal-clients#start-workflow-execution
        return WorkflowClient.start(workflow::initiateTransfer, transferDetails).getWorkflowId();
    }
}
