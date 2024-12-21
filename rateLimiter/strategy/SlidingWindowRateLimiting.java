package com.avinash.lld.rateLimiter.strategy;

import com.avinash.lld.rateLimiter.models.Window;

import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowRateLimiting implements RateLimiterStrategy {
    private final ConcurrentHashMap<String, Window> manager = new ConcurrentHashMap<>();
    private long maxRequests;
    private long timeWindowInMillis;

    public SlidingWindowRateLimiting(long maxRequests, long timeWindowInMillis) {
        this.maxRequests = maxRequests;
        this.timeWindowInMillis = timeWindowInMillis;
    }

    @Override
    public boolean tryAcquire(String userId, long currentTime) {
        Window window = manager.computeIfAbsent(userId,
                id -> new Window(currentTime));
        System.out.println("Window Created for " + userId);
        return window.tryAcquire(currentTime, timeWindowInMillis, maxRequests);
    }

    @Override
    public void cleanUp(String userId, long currentTime) {
        System.out.println("Cleanup for " + userId);
        manager.entrySet().removeIf(entry ->
                (currentTime - entry.getValue().getLastAccessTime()) > timeWindowInMillis);
    }
}
