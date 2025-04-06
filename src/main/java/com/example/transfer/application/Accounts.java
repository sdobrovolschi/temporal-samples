package com.example.transfer.application;

public interface Accounts {

    void withdrawMoney(TransactionDetails transaction);

    void depositMoney(TransactionDetails transaction);
}
