package org.example.ItemManagement;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.OrderManagement.Seller;

import java.sql.Time;

@Data
@AllArgsConstructor
public class FoodItem {
    // Compulsory Attributes;
    private String itemId;
    private int basePrice;
    private Seller seller;
    private Category category;
    private String itemName;

    private Cost price;
    private int inventory;



    // Not Compulsory
    private String image;
    private Rating rating; //  this will be used as filter
    private int buyCount = 0; // this will be used to give Suggestions
    private int productCategoryRank; // this will be used for enhancing the user Search Experience

    public FoodItem(FoodItemBuilder foodItemBuilder){
        this.seller = foodItemBuilder.seller;
        this.itemId = foodItemBuilder.itemId;
        this.basePrice = foodItemBuilder.basePrice;
        this.category = foodItemBuilder.category;
        this.itemName = foodItemBuilder.itemName;
        this.price = foodItemBuilder.price;
        this.inventory = foodItemBuilder.inventory;
        this.image = foodItemBuilder.image;
        this.rating = foodItemBuilder.rating;
        this.buyCount = foodItemBuilder.buyCount;
        this.productCategoryRank = foodItemBuilder.productCategoryRank;
    }

    // to update the buyCount
    private void updateBuyCount(){
         this.buyCount = this.buyCount + 1;
    }


    public static class FoodItemBuilder {

        private String itemId;

        private int basePrice;
        private Category category;
        private String itemName;
        private  Seller seller;


        private Cost price;
        private int inventory;



        // Not Compulsory
        private String image;
        private Rating rating; //  this will be used as filter
        private int buyCount = 0; // this will be used to give Suggestions
        private int productCategoryRank; // this will be used for enhancing the user Search Experience


        public  FoodItemBuilder(String itemId ,Category category,String itemName,int inventory,int basePrice)
        {
             this.itemId = itemId;
             this.category = category;
             this.itemName = itemName;
             this.inventory = inventory;
             this.basePrice = basePrice;


        }

        public FoodItemBuilder addImage(String image)
        {
            this.image = image;
            return this;
        }

        public FoodItemBuilder addRating(Rating rating)
        {
            this.rating = rating;
            return this;
        }

        public FoodItemBuilder addProductCategoryRank(int rank){
            this.productCategoryRank = rank;
            return this;
        }

        public FoodItem build(){
            return new FoodItem(this);
        }

    }
}
