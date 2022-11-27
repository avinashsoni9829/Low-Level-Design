package org.example.AdminPanel;

import lombok.Data;

import org.example.CouponManagement.Coupon;
import org.example.ItemManagement.FoodItem;
import org.example.OrderManagement.Order;
import org.example.OrderManagement.Seller;
import org.example.userManagement.Location;
import org.example.userManagement.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import java.util.Objects;
import java.util.Queue;

@Data
public class Admin implements AdminFunctions{
    private List<Seller> sellerList = new ArrayList<>();
    private Queue<OrderRequest> currentRequest = new LinkedList<>();
    private List<OrderRequest> requests = new ArrayList<>();
    private HashMap<String,OrderStatus> orderTrack = new HashMap<>();

    private int seller_count = 0;

    @Override
    public void showSellers() {
        System.out.println(sellerList);
    }



    @Override
    public void showAllOrderRequest() {
        System.out.println(requests);
        for(OrderRequest request : requests){
             showRequest(request);
        }
    }

    private void showRequest(OrderRequest request) {
        System.out.println("********************************************************************************");
        System.out.println("orderId = " + request.getOrderRequestId());
        System.out.println("order = " + request.getOrder());
        System.out.println("user name = " + request.getUser().getUsername());
        System.out.println("user address =" + request.getUser().getAddress().getAddress());
        System.out.println("Applied Coupon =" + request.getCouponsApplied());
        System.out.println("Additional Notes =" + request.getNote());

        System.out.println("********************************************************************************");
    }

    @Override
    public void updateOrderRequestStatus(String orderId  , OrderStatus status) {
         orderTrack.put(orderId,status);
    }

    @Override
    public void acceptSeller(Seller seller) {
        sellerList.add(seller);

    }

    @Override
    public void acceptOrder(OrderRequest request) {
        currentRequest.add(request);
        requests.add(request);
    }

    @Override
    public Seller createSeller() {

        Seller seller = new Seller("123"+seller_count,"avx",new Location(123,142,"abc",
                "15012","okd","hf","fff","f3d","gff"),new ArrayList<FoodItem>());
        seller_count = seller_count + 1;
        return seller;
    }

    @Override
    public void processCurrentOrder() {
        OrderRequest request = currentRequest.poll();

        double distance = calculateDistance(request.getSeller(),request.getUser());
        int time_in_seconds =(int) distance / 10;

        // Check Inventory
        for(Order order : request.getOrder()){
              int qty = order.getQty();
              int inventory = order.getFoodItem().getInventory();
              if(qty  > inventory){
                  System.out.println("Sorry We are out of inventory ! We Are Sorry for this!");
                  request.setOrderStatus(OrderStatus.CANCELLED_BY_SELLER);
                  break;
              }
              else {
                  // decrease the inventory
                   order.getFoodItem().setInventory(inventory-qty);
              }
        }


        // Check Total Bill and User Amount
        if(Objects.equals(request.getOrderStatus(),OrderStatus.INITIATED_BY_USER)){
             int total_bill = 0;
             int savings = 0;

             for(Order order : request.getOrder()){
                  total_bill = total_bill + (order.getPrice() * order.getQty());

             }
            System.out.println("Your Total Bill is  = "  + total_bill);
            if(Objects.nonNull(request.getCouponsApplied())){
                System.out.println("Congratulations! you have a coupon applied to this order !" + request.getCouponsApplied().getCouponName());
                System.out.println("Checking  Coupon details  ... ");
                int final_price = applyCoupon(total_bill,request.getCouponsApplied());
                System.out.println("Your Final Price is : " + final_price);

                System.out.println("Your Total Saving in the Order = " + (total_bill - final_price));

            }
            request.setOrderStatus(OrderStatus.ACCEPTED_BY_SELLER);
        }

         if(Objects.equals(request.getOrderStatus(),OrderStatus.ACCEPTED_BY_SELLER)){
             System.out.println("Your Order is Accepted By Seller and Will be Delivered To  you in " + time_in_seconds + "seconds");
         }
   }

    public  int applyCoupon(int total, Coupon couponsApplied) {
        int order_price = total;
        double coupon = couponsApplied.getDiscount_percentage()/100;


        if(order_price > couponsApplied.getMin_limit() && order_price < couponsApplied.getMax_limit()){
             //eligible for coupon
            System.out.println("Congratulations! we have applied " + couponsApplied.getCouponName() + "on Your Order");

            double discount = coupon * order_price ;

            int agreed = Math.min((int)discount,couponsApplied.getCoupon_limit());

            System.out.println("Your Are eligible for a discount of " + agreed + "on your bill");

            order_price = order_price - agreed;
        }

        return order_price;
    }

    public double calculateDistance(Seller seller, User user) {
        double distance = Math.sqrt(((seller.getSeller_address().getX_coordinate() - user.getAddress().getX_coordinate()) *
                (seller.getSeller_address().getX_coordinate() - user.getAddress().getX_coordinate())) + ((seller.getSeller_address().getY_coordinate() - user.getAddress().getY_coordinate()) *
                (seller.getSeller_address().getY_coordinate() - user.getAddress().getY_coordinate()))
        );

        return distance;
    }


}
