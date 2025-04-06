package com.example.transfer.infrastructure.collaborators;

import com.example.transfer.application.Accounts;
import com.example.transfer.application.TransactionDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.example.transfer.infrastructure.collaborators.AccountsApi.TransactionRequest.Type.DEPOSIT;
import static com.example.transfer.infrastructure.collaborators.AccountsApi.TransactionRequest.Type.WITHDRAWAL;

@Component
@RequiredArgsConstructor
public final class FeignAccounts implements Accounts {

    private final AccountsApi accountsApi;

    @Override
    public void withdrawMoney(TransactionDetails transaction) {
        var request = new AccountsApi.TransactionRequest(WITHDRAWAL, transaction.amount());
        accountsApi.withdrawMoney(transaction.accountId(), request);
    }

    @Override
    public void depositMoney(TransactionDetails transaction) {
        var request = new AccountsApi.TransactionRequest(DEPOSIT, transaction.amount());
        accountsApi.depositMoney(transaction.accountId(), request);
    }
}
