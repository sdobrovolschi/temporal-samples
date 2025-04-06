package com.example.transfer.application;

import lombok.RequiredArgsConstructor;

import java.util.UUID;

import static java.util.UUID.randomUUID;

@RequiredArgsConstructor
public final class DefaultMoneyTransferService implements MoneyTransferUseCase {

    private final Transfers transfers;

    @Override
    public UUID initiateTransfer(TransferDetails transfer) {
        // input validation

        var transferId = randomUUID();

        transfers.add(transferId, transfer);

        return transferId;
    }
}
