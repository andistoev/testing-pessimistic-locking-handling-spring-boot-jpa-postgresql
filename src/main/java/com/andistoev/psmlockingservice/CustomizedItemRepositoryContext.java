package com.andistoev.psmlockingservice;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
public abstract class CustomizedItemRepositoryContext {

    @Getter
    @Value("${concurrency.pessimisticLocking.requiredToSetLockTimeoutForTestsAtStartup: false}")
    private boolean requiredToSetLockTimeoutForTestsAtStartup;

    @Value("${concurrency.pessimisticLocking.requiredToSetLockTimeoutForEveryQuery: true}")
    private boolean requiredToSetLockTimeoutForEveryQuery;

    @Getter
    @Value("${concurrency.pessimisticLocking.requiredToSetLockTimeoutQueryHint: false}")
    private boolean requiredToSetLockTimeoutQueryHint;

    @Getter
    @Value("${concurrency.pessimisticLocking.delayAtTheEndOfTheQueryForPessimisticLockingTestingInMs: 0}")
    private long delayAtTheEndOfTheQueryForPessimisticLockingTestingInMs;

    @Getter
    @Value("${concurrency.pessimisticLocking.minimalPossibleLockTimeOutInMs: 1}")
    private long minimalPossibleLockTimeOutInMs;

    @Getter
    @Value("${concurrency.pessimisticLocking.lockTimeOutInMsForQueryGetItem: 5000}")
    private long lockTimeOutInMsForQueryGetItem;

    protected final EntityManager em;

    protected void setLockTimeout(long timeoutDurationInMs) {
        Query query = em.createNativeQuery("set local lock_timeout = " + timeoutDurationInMs);
        query.executeUpdate();
    }

    private static final String[] TIME_MEASURES = {"ms", "s", "min", "h", "d"};
    private static final TimeUnit[] TIME_UNITS = {TimeUnit.MILLISECONDS, TimeUnit.SECONDS, TimeUnit.MINUTES, TimeUnit.HOURS, TimeUnit.DAYS};

    private long parseLockTimeOutToMilliseconds(String lockTimeOut) {
        for (int idx = 0; idx < TIME_MEASURES.length; idx++) {
            if (lockTimeOut.contains(TIME_MEASURES[idx])) {
                return Long.valueOf(lockTimeOut.substring(0, lockTimeOut.length() - TIME_MEASURES[idx].length())) * TIME_UNITS[idx].toMillis(1);
            }
        }

        return Long.valueOf(lockTimeOut);
    }

    protected long getLockTimeout() {
        Query query = em.createNativeQuery("show lock_timeout");
        String result = (String) query.getSingleResult();
        return parseLockTimeOutToMilliseconds(result);
    }

    protected Query setLockTimeoutIfRequired(Query query) {
        if (requiredToSetLockTimeoutForEveryQuery) {
            log.info("... set lockTimeOut {} ms through native query ...", getLockTimeOutInMsForQueryGetItem());
            setLockTimeout(getLockTimeOutInMsForQueryGetItem());
        }

        if (requiredToSetLockTimeoutQueryHint) {
            log.info("... set lockTimeOut {} ms through query hint ...", getLockTimeOutInMsForQueryGetItem());
            query.setHint("javax.persistence.lock.timeout", String.valueOf(getLockTimeOutInMsForQueryGetItem()));
        }

        return query;
    }

    protected void insertArtificialDealyAtTheEndOfTheQueryForTestsOnly() {
        // for testing purposes only
    }
}
