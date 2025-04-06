package com.example.temporal;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface MoneyTransferWorkflow {

    @WorkflowMethod(name = "transfer-initiation")
    void initiateTransfer(TransferDetails transfer);
}
