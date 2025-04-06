package com.example.transfer.application;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DefaultAccountService implements AccountUseCases {

    private final Accounts accounts;

    @Override
    public void withdrawMoney(TransactionDetails transaction) {
        accounts.withdrawMoney(transaction);
    }

    @Override
    public void depositMoney(TransactionDetails transaction) {
        accounts.depositMoney(transaction);
    }
}
