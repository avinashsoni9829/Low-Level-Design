package org.example.strategy;

public class WeightBasedDelivery implements DeliveryStrategy {
    private final int costPerKg = 5;


    @Override
    public double calculateShippingCharge(Order order) {
        return costPerKg * order.getWeight();
    }
}
