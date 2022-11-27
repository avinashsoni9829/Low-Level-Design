package org.example.OrderManagement;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.ItemManagement.FoodItem;
import org.example.userManagement.Location;

import java.util.List;


@Data
@AllArgsConstructor
public class Seller {
    private String sellerId;
    private String sellerName;
    private Location seller_address;
    private List<FoodItem> products;
}
