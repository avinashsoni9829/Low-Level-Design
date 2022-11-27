package org.example.userManagement;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
public class Location {
   @NotNull
   private double x_coordinate;
   @NotNull
    private double y_coordinate;
    private String country;
    private String zipcode;
    private String state;
    private String city;
    private String zone;
    private String address;
    private String landmark;
}
