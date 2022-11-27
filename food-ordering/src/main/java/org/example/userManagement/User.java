package org.example.userManagement;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.OrderManagement.Order;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
public class User {
    private String username;
    private String email;
    private String password;

    @NotBlank
    @Size(min = 10,max = 10)
    private String phoneNumber;
    private Location address;
    private int balance;
    private List<Order> myOrders;

}
