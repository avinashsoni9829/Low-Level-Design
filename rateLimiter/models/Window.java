package com.avinash.lld.rateLimiter.models;

import java.util.concurrent.atomic.LongAdder;

public class Window {
    private final LongAdder counter = new LongAdder();
    private volatile long windowStart;
    private volatile long lastAccessTime;

    public Window(long currentTime) {
        this.windowStart = currentTime;
        this.lastAccessTime = currentTime;
    }

    public boolean tryAcquire(long currentTime, long timeWindowInMillis, long maxRequests) {
        lastAccessTime = currentTime;

        // Reset window if time has exceeded the window period
        if (currentTime - windowStart > timeWindowInMillis) {
            synchronized (this) {
                System.out.println("Locked");
                if (currentTime - windowStart > timeWindowInMillis) {
                    counter.reset();
                    windowStart = currentTime;
                    System.out.println("Counter = " + counter.sum() + "window start - " + windowStart);
                }
            }
            System.out.println("Unlocked");
        }

        counter.increment();
        return counter.sum() <= maxRequests;
    }

    public long getLastAccessTime() {
        return lastAccessTime;
    }
}
