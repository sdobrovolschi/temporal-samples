package com.example.transfer.infrastructure.config;

import com.example.transfer.application.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class Config {

    @Bean
    MoneyTransferUseCase moneyTransferUseCase(Transfers transfers) {
        return new DefaultMoneyTransferService(transfers);
    }

    @Bean
    AccountUseCases accountUseCases(Accounts accounts) {
        return new DefaultAccountService(accounts);
    }
}
