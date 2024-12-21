package com.avinash.lld.rateLimiter.models;

public class TokenBucket {
    private volatile long tokens;
    private final long maxTokens;
    private final long refillRate;
    private volatile long lastRefill;
    private volatile long lastAccessTime;

    public TokenBucket(long maxTokens, long refillRate, long currentTime) {
        this.maxTokens = maxTokens;
        this.refillRate = refillRate;
        this.tokens = maxTokens;
        this.lastRefill = currentTime;
        this.lastAccessTime = currentTime;
    }

    public boolean tryConsume(long currentTime) {
        refillTokens(currentTime);
        lastAccessTime = currentTime;
        if (tokens > 0) {
            tokens--;
            return true; // Token granted
        }
        return false; // Token exhausted
    }

    public void refillTokens(long currentTime) {
        long timeElapsedAfterLastRefill = currentTime - lastRefill;
        if (timeElapsedAfterLastRefill >= refillRate) {
            System.out.println("Lock taken ");
            synchronized (this) {
                long newTokens = timeElapsedAfterLastRefill / refillRate;
                tokens = Math.min(maxTokens, tokens + newTokens);
                lastRefill = currentTime;
                System.out.println("Tokens refilled. Current tokens: " + tokens);
            }
            System.out.println("Lock released");
        }
    }

    public long getLastAccessTime() {
        return lastAccessTime;
    }
}
