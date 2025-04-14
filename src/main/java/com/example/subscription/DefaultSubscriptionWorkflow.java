package com.example.subscription;

import com.example.transfer.infrastructure.temporal.TaskQueue;
import io.temporal.activity.ActivityOptions;
import io.temporal.spring.boot.WorkflowImpl;
import io.temporal.workflow.Workflow;

import java.time.Duration;

@WorkflowImpl(taskQueues = TaskQueue.SUBSCRIPTIONS)
public class DefaultSubscriptionWorkflow implements SubscriptionWorkflow {

    private final SubscriptionActivities subscriptionActivities =
            io.temporal.workflow.Workflow.newActivityStub(
                    SubscriptionActivities.class,
                    ActivityOptions.newBuilder()
                            .setTaskQueue(TaskQueue.SUBSCRIPTIONS)
                            // The Temporal Server doesn't detect failures when a Worker loses communication with the Server or crashes.
                            // Therefore, the Temporal Server relies on the Start-To-Close Timeout to force Activity retries.
                            // https://docs.temporal.io/encyclopedia/detecting-activity-failures#start-to-close-timeout
                            .setStartToCloseTimeout(Duration.ofSeconds(2))
                            .build());

    private boolean active = true;

    @Override
    public void start() {
        while (active) {
            Workflow.sleep(Duration.ofSeconds(10));

            subscriptionActivities.chargeMonthlyFee();
        }
        subscriptionActivities.sendSorryToSeeYouGoEmail();
    }

    @Override
    public void cancel() {
        active = false;
    }
}
