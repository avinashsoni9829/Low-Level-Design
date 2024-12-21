package com.avinash.lld.rateLimiter;

import com.avinash.lld.rateLimiter.strategy.RateLimiterStrategy;
import com.avinash.lld.rateLimiter.strategy.SlidingWindowRateLimiting;
import com.avinash.lld.rateLimiter.strategy.TokenBucketRateLimiting;

public class Runner {
    public static void main(String[] args) {
        // Initialize Sliding Window Rate Limiter
        RateLimiterStrategy slidingWindowStrategy = new SlidingWindowRateLimiting(5, 1000);
        RateLimiter slidingWindowLimiter = new RateLimiter(slidingWindowStrategy, 5000);

        // Initialize Token Bucket Rate Limiter
        RateLimiterStrategy tokenBucketStrategy = new TokenBucketRateLimiting(5, 400);
        RateLimiter tokenBucketLimiter = new RateLimiter(tokenBucketStrategy, 5000);

        String user = "user";

//        System.out.println("Testing Sliding Window Rate Limiter:");
//        for (int i = 0; i < 20; ++i) {
//            boolean allowed = slidingWindowLimiter.tryAcquire(user);
//            System.out.println("Request " + (i + 1) + ": " + (allowed ? "Allowed" : "Rejected"));
//            try {
//                Thread.sleep(100); // Simulate 100 ms interval between requests
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
//        }

        System.out.println("\nTesting Token Bucket Rate Limiter:");
        for (int i = 0; i < 20; ++i) {
            boolean allowed = tokenBucketLimiter.tryAcquire(user);
            System.out.println("Request " + (i + 1) + ": " + (allowed ? "Allowed" : "Rejected"));
            try {
                Thread.sleep(100); // Simulate 100 ms interval between requests
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Shut down both rate limiters
        slidingWindowLimiter.shutDown();
        tokenBucketLimiter.shutDown();
    }
}
