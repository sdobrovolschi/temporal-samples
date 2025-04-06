package com.example.temporal;

import java.util.UUID;

public record TransactionDetails(UUID accountId, Money amount) {

}
