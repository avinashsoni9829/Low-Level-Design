package org.example.ItemManagement;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Cost {
    private int basePrice;
    private int packagingCharge;
    private int taxes;
    private int surgeCharge;
    private int deliveryCharge;



}
