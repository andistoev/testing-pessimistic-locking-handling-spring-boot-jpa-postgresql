package com.andistoev.psmlockingservice;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
@Profile("!test")
public class CustomizedItemRepositoryContextImpl extends CustomizedItemRepositoryContext {

    public CustomizedItemRepositoryContextImpl(EntityManager em) {
        super(em);
    }
}

