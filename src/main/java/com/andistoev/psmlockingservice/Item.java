package com.andistoev.psmlockingservice;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public class Item {

    @Id
    private UUID id = UUID.randomUUID();

    private int amount = 0;
}
