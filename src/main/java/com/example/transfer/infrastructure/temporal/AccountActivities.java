package com.example.transfer.infrastructure.temporal;

import com.example.transfer.application.TransactionDetails;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;
import io.temporal.common.MethodRetry;

@ActivityInterface
public interface AccountActivities {

    @ActivityMethod(name = "account-deposit")
    @MethodRetry(initialIntervalSeconds = 5, backoffCoefficient = 1)
    void depositMoney(TransactionDetails transaction);

    @ActivityMethod(name = "account-withdrawal")
    @MethodRetry(initialIntervalSeconds = 5, backoffCoefficient = 1)
    void withdrawMoney(TransactionDetails transaction);
}
