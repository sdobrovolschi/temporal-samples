package com.example.transfer.application;

import java.util.UUID;

public interface Transfers {

    void add(UUID transferId, TransferDetails transfer);
}
