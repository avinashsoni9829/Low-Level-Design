package org.example.Machine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class User {
    private String username;
    private String password;
    private int balance;
    private boolean isAdmin = false;
}
