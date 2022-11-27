package org.example;

import org.example.AdminPanel.Admin;
import org.example.AdminPanel.OrderRequest;
import org.example.AdminPanel.OrderStatus;
import org.example.CouponManagement.Coupon;
import org.example.ItemManagement.Category;
import org.example.ItemManagement.Cost;
import org.example.ItemManagement.FoodItem;
import org.example.OrderManagement.Order;
import org.example.OrderManagement.Seller;
import org.example.userManagement.Location;
import org.example.userManagement.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Admin admin = new Admin();

        Location location = new Location(11,146,"INDIA","302018",
                "Rajasthan","Jaipur","D","p/3 gopalnagar","near hawa mahal");
        // Create a User
        User user = new User("avinash","avinashsoni@gmail.com","1234","1452536987",location,10000,null);

        Seller seller =  admin.createSeller();

        //Create food item
        FoodItem coffee = new FoodItem.FoodItemBuilder("123", Category.COFFEE,"cold-coffee",100,80).build();
        // Adding Food Item to Seller
        FoodItem pizza = new FoodItem.FoodItemBuilder("1231", Category.PIZZA,"cheese-pizza",10,180).build();

        List<FoodItem> products = new ArrayList<>();
        products.add(coffee);
        products.add(pizza);

        seller.setProducts(products);

        admin.acceptSeller(seller);

        // Creating Order for Coffee


        Cost cost  = new Cost(coffee.getBasePrice(),20,(int)(0.18* coffee.getBasePrice()),0,(int)(0.3 * admin.calculateDistance(seller,user)));

        Order order1 = new Order(2,"1",seller,coffee,cost);

        // Creating Order for Pizza

        Cost cost_pizza  = new Cost(pizza.getBasePrice(),20,(int)(0.18* pizza.getBasePrice()),0,(int)(0.3 * admin.calculateDistance(seller,user)));

        Order order2 = new Order(5,"2",seller,pizza,cost_pizza);

        // Creating Order Request
        // Creating Coupon
        Coupon coupon = new Coupon("c1","C12",20,100,10000,50);

        OrderRequest request = new OrderRequest(seller,"Order1", Arrays.asList(order1,order2), OrderStatus.INITIATED_BY_USER,user,coupon,"make it fast");

        admin.acceptOrder(request);
        admin.showAllOrderRequest();
        admin.processCurrentOrder();

    }
}
