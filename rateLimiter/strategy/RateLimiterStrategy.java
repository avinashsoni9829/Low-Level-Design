package com.avinash.lld.rateLimiter.strategy;

public interface RateLimiterStrategy {
    boolean tryAcquire(String userId, long currentTime);
    void cleanUp(String userId,long currentTime);
}
