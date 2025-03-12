package com.example.beprojec2.Entity.keys;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;

import java.io.Serializable;

@Embeddable
public class ResCateKey implements Serializable {
    private int restaurantid;
    private int categoryid;
    public ResCateKey() {}
    public ResCateKey(int restaurantid, int categoryid) {
        this.restaurantid = restaurantid;
        this.categoryid = categoryid;
    }
    public int getRestaurantid() {
        return restaurantid;
    }

    public void setRestaurantid(int restaurantid) {
        this.restaurantid = restaurantid;
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }
}
