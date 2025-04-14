package com.example.subscription;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface SubscriptionActivities {

    @ActivityMethod(name = "subscription-charge-monthly-fee")
    void chargeMonthlyFee();

    @ActivityMethod(name = "subscription-sorry-to-see-you-go-email")
    void sendSorryToSeeYouGoEmail();
}
