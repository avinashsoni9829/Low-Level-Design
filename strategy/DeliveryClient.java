package org.example.strategy;

public class DeliveryClient {

    public static void main( String[] args ){
           Order myOrder = new Order(1,60,5,20);
           StrategyFactory strategyFactory = new StrategyFactory();
           // standard delivery
           ShippingChargeCalculator chargeCalculator = new ShippingChargeCalculator(strategyFactory.getStrategy("standard"),myOrder);
           chargeCalculator.getShippingCharge();
           // weighted delivery
           chargeCalculator = new ShippingChargeCalculator(strategyFactory.getStrategy("weighted"),myOrder);
           chargeCalculator.getShippingCharge();
           // distance delivery
           chargeCalculator = new ShippingChargeCalculator(strategyFactory.getStrategy("distance"),myOrder);
           chargeCalculator.getShippingCharge();
    }
}
