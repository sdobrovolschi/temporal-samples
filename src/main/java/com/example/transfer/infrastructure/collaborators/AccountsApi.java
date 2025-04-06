package com.example.transfer.infrastructure.collaborators;

import com.example.transfer.lang.Money;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(name = "customers", url = "${accounts.base-url}")
public interface AccountsApi {

    @PostMapping(path = "/accounts/{accountId}/transactions", consumes = APPLICATION_JSON_VALUE)
    void depositMoney(@PathVariable("accountId") UUID accountId, @RequestBody TransactionRequest request);

    @PostMapping(path = "/accounts/{accountId}/transactions", consumes = APPLICATION_JSON_VALUE)
    void withdrawMoney(@PathVariable("accountId") UUID accountId, @RequestBody TransactionRequest request);

    record TransactionRequest(Type type, Money amount) {

        public enum Type {
            DEPOSIT, WITHDRAWAL;

            @JsonValue
            @Override
            public String toString() {
                return super.toString();
            }
        }
    }
}
