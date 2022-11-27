package org.example.OrderManagement;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.ItemManagement.Cost;
import org.example.ItemManagement.FoodItem;
import org.example.userManagement.User;

import java.time.LocalDateTime;


@Data
public class Order {
    private LocalDateTime dateTime = LocalDateTime.now();

    private int qty;
    private String orderId;
    private Seller seller;
    private FoodItem foodItem;
    private int price;

    public  Order(int qty,String id, Seller seller,FoodItem item,Cost cost){
        this.qty = qty;
        this.orderId = id;
        this.seller = seller;
        this.foodItem = item;
       this.price = cost.getBasePrice() + cost.getPackagingCharge() + cost.getSurgeCharge() + cost.getTaxes() + cost.getDeliveryCharge();
    }
}
