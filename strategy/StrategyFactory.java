package org.example.strategy;

public class StrategyFactory {

    public  DeliveryStrategy getStrategy(String strategyName){
        switch (strategyName){
            case "standard": return new StandardDelivery();
            case "weighted": return new WeightBasedDelivery();
            case "distance": return new DistanceBasedDelivery();
            default: throw  new RuntimeException("Not a Valid Strategy !");
        }
    }
}
