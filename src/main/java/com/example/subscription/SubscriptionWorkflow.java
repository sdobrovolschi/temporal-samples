package com.example.subscription;

import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface SubscriptionWorkflow {

    @WorkflowMethod(name = "subscription")
    void start();

    @SignalMethod(name = "subscription")
    void cancel();
}
