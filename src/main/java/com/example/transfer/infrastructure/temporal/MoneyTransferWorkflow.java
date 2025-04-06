package com.example.transfer.infrastructure.temporal;

import com.example.transfer.application.TransferDetails;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface MoneyTransferWorkflow {

    @WorkflowMethod(name = "transfer-initiation")
    void initiateTransfer(TransferDetails transfer);
}
