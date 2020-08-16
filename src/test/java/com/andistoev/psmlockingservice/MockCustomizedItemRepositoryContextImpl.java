package com.andistoev.psmlockingservice;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.concurrent.TimeUnit;

@Component
@Profile("test")
public class MockCustomizedItemRepositoryContextImpl extends CustomizedItemRepositoryContext {

    public MockCustomizedItemRepositoryContextImpl(EntityManager em) {
        super(em);
    }

    @Override
    protected void setLockTimeout(long timeoutDurationInMs) {
        long timeoutDurationInSec = TimeUnit.MILLISECONDS.toSeconds(timeoutDurationInMs);
        Query query = em.createNativeQuery(String.format("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY('derby.locks.waitTimeout',  '%d')", timeoutDurationInSec));
        query.executeUpdate();
    }

    @Override
    protected long getLockTimeout() {
        Query query = em.createNativeQuery("VALUES SYSCS_UTIL.SYSCS_GET_DATABASE_PROPERTY('derby.locks.waitTimeout')");
        long timeoutDurationInSec = Long.valueOf((String) query.getSingleResult());
        return TimeUnit.SECONDS.toMillis(timeoutDurationInSec);
    }
}

