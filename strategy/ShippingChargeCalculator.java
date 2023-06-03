package org.example.strategy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ShippingChargeCalculator {

    private DeliveryStrategy deliveryStrategy;
    private Order order;

    ShippingChargeCalculator(DeliveryStrategy strategy,Order order){
        this.deliveryStrategy = strategy;
        this.order = order;
    }

    public void getShippingCharge(){
       System.out.println("Your delivery cost is :" + deliveryStrategy.calculateShippingCharge(order));
    }
}
