package com.example.transfer.application;

import com.example.transfer.lang.Money;

import java.util.UUID;

public record TransactionDetails(UUID accountId, Money amount) {

}
