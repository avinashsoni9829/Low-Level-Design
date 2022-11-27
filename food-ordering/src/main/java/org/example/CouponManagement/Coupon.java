package org.example.CouponManagement;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Coupon {
    private String couponId;
    private String couponName;
    private double discount_percentage;
    private int min_limit;
    private int max_limit;
    private int coupon_limit;  // upto Cases

}
