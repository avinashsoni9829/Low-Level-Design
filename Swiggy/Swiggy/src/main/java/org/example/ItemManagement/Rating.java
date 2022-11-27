package org.example.ItemManagement;

import lombok.Data;

import java.util.List;

@Data
public class Rating {
    private int rating;
    private List<Integer> recent;
    private List<String> comments;

}
