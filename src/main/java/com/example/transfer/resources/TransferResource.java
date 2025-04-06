package com.example.transfer.resources;

import com.example.transfer.application.MoneyTransferUseCase;
import com.example.transfer.application.TransferDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "/transfers")
@Slf4j
@RequiredArgsConstructor
class TransferResource {

    private final MoneyTransferUseCase moneyTransferUseCase;

    @PostMapping
    UUID start(@RequestBody TransferDetails transfer) {
        return moneyTransferUseCase.initiateTransfer(transfer);
    }
}
