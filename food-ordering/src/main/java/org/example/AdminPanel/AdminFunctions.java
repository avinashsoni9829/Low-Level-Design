package org.example.AdminPanel;

import org.example.ItemManagement.FoodItem;
import org.example.OrderManagement.Order;
import org.example.OrderManagement.Seller;

import java.util.List;

public interface AdminFunctions {
    public void showSellers();

    public void showAllOrderRequest();

    public void updateOrderRequestStatus(String orderId,OrderStatus status);


    public void acceptSeller(Seller seller);

    public void acceptOrder(OrderRequest request);

    public Seller createSeller();

    public void processCurrentOrder();




}
