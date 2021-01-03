package com.andistoev.psmlockingservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void incrementAmount(UUID id, int amount) {
        Item item = itemRepository.getItemAndObtainPessimisticWriteLockingOnItById(id);
        item.setAmount(item.getAmount() + amount);
    }

}
