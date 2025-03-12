package com.example.beprojec2.Entity.keys;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RatingFoodKey implements Serializable {
    private int foodid;
    private int restaurantid;
    private int accountid;
    public RatingFoodKey() {}
    public RatingFoodKey( int accountid,int foodid, int restaurantid) {
        this.foodid = foodid;
        this.restaurantid = restaurantid;
        this.accountid = accountid;

    }
    public int getFoodid() {
        return foodid;
    }

    public void setFoodid(int foodid) {
        this.foodid = foodid;
    }

    public int getRestaurantid() {
        return restaurantid;
    }

    public void setRestaurantid(int restaurantid) {
        this.restaurantid = restaurantid;
    }

    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }

}
