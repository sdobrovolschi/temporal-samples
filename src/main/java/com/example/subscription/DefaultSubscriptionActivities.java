package com.example.subscription;

import com.example.transfer.infrastructure.temporal.TaskQueue;
import io.temporal.spring.boot.ActivityImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@ActivityImpl(taskQueues = TaskQueue.SUBSCRIPTIONS)
@Slf4j
public class DefaultSubscriptionActivities implements SubscriptionActivities {

    @Override
    public void chargeMonthlyFee() {
        log.info("Charging monthly fee...");
    }

    @Override
    public void sendSorryToSeeYouGoEmail() {
        log.info("Sending see you go email...");
    }
}
