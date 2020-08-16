package com.andistoev.psmlockingservice;

import java.util.UUID;

public interface CustomizedItemRepository {

    void setLockTimeout(long timeoutDurationInMs);

    long getLockTimeout();

    Item getItemAndObtainPessimisticWriteLockingOnItById(UUID id);
}
