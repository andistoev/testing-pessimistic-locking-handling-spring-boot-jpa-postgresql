package com.andistoev.psmlockingservice;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ItemRepository extends CrudRepository<Item, UUID>, CustomizedItemRepository {

}
