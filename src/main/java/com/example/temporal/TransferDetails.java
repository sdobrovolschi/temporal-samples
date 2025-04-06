package com.example.temporal;

import java.util.UUID;

public record TransferDetails(UUID fromAccountId, UUID toAccountId, Money amount) {
}
