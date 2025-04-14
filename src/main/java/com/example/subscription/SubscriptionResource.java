package com.example.subscription;

import com.example.transfer.infrastructure.temporal.TaskQueue;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.UUID;

import static io.temporal.api.enums.v1.WorkflowIdConflictPolicy.WORKFLOW_ID_CONFLICT_POLICY_FAIL;
import static io.temporal.api.enums.v1.WorkflowIdReusePolicy.WORKFLOW_ID_REUSE_POLICY_REJECT_DUPLICATE;
import static java.util.UUID.randomUUID;

@RestController
@RequestMapping(path = "/subscriptions")
@RequiredArgsConstructor
public class SubscriptionResource {

    private final WorkflowClient client;

    @PostMapping
    UUID start() {
        var subscriptionId = randomUUID();

        var workflow = newWorkflow(subscriptionId);

        WorkflowClient.start(workflow::start).getWorkflowId();

        return subscriptionId;
    }

    @DeleteMapping(path = "/{subscriptionId}")
    void cancel(@PathVariable("subscriptionId") UUID subscriptionId) {
        var workflow = client.newWorkflowStub(SubscriptionWorkflow.class, subscriptionId.toString());

        workflow.cancel();
    }

    private SubscriptionWorkflow newWorkflow(UUID subscriptionId) {
        return client.newWorkflowStub(SubscriptionWorkflow.class,
                WorkflowOptions.newBuilder()
                        .setTaskQueue(TaskQueue.SUBSCRIPTIONS)
                        .setWorkflowId(subscriptionId.toString())
                        .setWorkflowIdReusePolicy(WORKFLOW_ID_REUSE_POLICY_REJECT_DUPLICATE)
                        .setWorkflowIdConflictPolicy(WORKFLOW_ID_CONFLICT_POLICY_FAIL)
                        // This timeout is primarily available to recognize whether a Worker has gone down so that the Workflow Execution can be recovered on a different Worker.
                        // https://docs.temporal.io/encyclopedia/detecting-workflow-failures#workflow-task-timeout
                        .setWorkflowTaskTimeout(Duration.ofSeconds(5))
                        .build());
    }
}
