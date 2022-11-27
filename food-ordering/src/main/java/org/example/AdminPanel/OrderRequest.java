package org.example.AdminPanel;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.CouponManagement.Coupon;
import org.example.OrderManagement.Order;
import org.example.OrderManagement.Seller;
import org.example.userManagement.User;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderRequest {
    private Seller seller;
    private String orderRequestId;
    private List<Order> order;
    private OrderStatus orderStatus;
    private User user;
    private Coupon couponsApplied;
    private String note;

}
