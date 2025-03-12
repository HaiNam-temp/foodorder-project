package com.example.beprojec2.Entity.keys;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class RatingRestaurantKey implements Serializable {
    private int restaurantid;
    private int accountid;
    private int restaurandid;
    public RatingRestaurantKey(int restaurantid, int accountid, int restaurandid) {
        this.restaurantid = restaurantid;
        this.accountid = accountid;
        this.restaurandid = restaurandid;

    }
    public RatingRestaurantKey() {}
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

    public int getRestaurandid() {
        return restaurandid;
    }

    public void setRestaurandid(int restaurandid) {
        this.restaurandid = restaurandid;
    }
}
