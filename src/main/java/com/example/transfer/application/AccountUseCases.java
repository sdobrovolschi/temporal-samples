package com.example.transfer.application;

public interface AccountUseCases {

    void withdrawMoney(TransactionDetails transaction);

    void depositMoney(TransactionDetails transaction);
}
