package org.example.strategy;

public class StandardDelivery  implements DeliveryStrategy{
    @Override
    public double calculateShippingCharge(Order order) {
        return order.getStandardDeliveryCost();
    }
}
