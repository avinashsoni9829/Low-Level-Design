package org.example.strategy;

public class DistanceBasedDelivery implements DeliveryStrategy{
    private  final int costPerKm = 10;
    @Override
    public double calculateShippingCharge(Order order) {
        return  costPerKm * order.getDistanceInKm();
    }
}
