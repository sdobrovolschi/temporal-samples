package com.example.transfer.application;

import java.util.UUID;

public interface MoneyTransferUseCase {

    UUID initiateTransfer(TransferDetails transfer);
}
