package com.avinash.lld.rateLimiter.strategy;

import com.avinash.lld.rateLimiter.models.TokenBucket;

import java.util.concurrent.ConcurrentHashMap;

public class TokenBucketRateLimiting implements RateLimiterStrategy {
    private final ConcurrentHashMap<String, TokenBucket> manager = new ConcurrentHashMap<>();
    private final long maxTokens;
    private final long refillRate;

    public TokenBucketRateLimiting(long maxTokens, long refillRate) {
        this.maxTokens = maxTokens;
        this.refillRate = refillRate;
    }

    @Override
    public boolean tryAcquire(String userId, long currentTime) {
        TokenBucket bucket = manager.computeIfAbsent(userId,
                id -> new TokenBucket(maxTokens, refillRate, currentTime));
        System.out.println("Token Bucket Created for " + userId);
        return bucket.tryConsume(currentTime);
    }

    @Override
    public void cleanUp(String userId, long currentTime) {
        System.out.println("Token Bucket Cleanup for " + userId);
        manager.entrySet().removeIf(entry ->
                (currentTime - entry.getValue().getLastAccessTime()) > refillRate * 2);
    }
}
