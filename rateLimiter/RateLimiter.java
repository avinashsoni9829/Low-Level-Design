package com.avinash.lld.rateLimiter;

import com.avinash.lld.rateLimiter.strategy.RateLimiterStrategy;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RateLimiter {
    private RateLimiterStrategy strategy;
    private ScheduledExecutorService scheduler;

    public RateLimiter(RateLimiterStrategy strategy, long evictionInterval) {
        this.strategy = strategy;
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
        this.scheduler.scheduleAtFixedRate(() ->
                strategy.cleanUp(null, System.currentTimeMillis()), evictionInterval, evictionInterval, TimeUnit.MILLISECONDS);
    }

    public boolean tryAcquire(String userId) {
        return strategy.tryAcquire(userId, System.currentTimeMillis());
    }

    public void shutDown() {
        scheduler.shutdown();
    }
}
