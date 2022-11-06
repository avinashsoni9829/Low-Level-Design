package org.example.Machine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Location {
    private String country;
    private String zipcode;
    private String state;
    private String city;
    private String zone;
    private String address;
}
