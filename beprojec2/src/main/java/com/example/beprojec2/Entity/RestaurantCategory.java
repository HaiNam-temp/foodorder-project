package com.example.beprojec2.Entity;

import com.example.beprojec2.Entity.keys.ResCateKey;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="restaurantcategory")
public class RestaurantCategory {
    @EmbeddedId
    private ResCateKey resCateKey;

    @ManyToOne
    @JoinColumn(name="restaurantid",insertable=false,updatable=false)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name="categoryid",insertable=false,updatable=false)
    private Category category;

    public ResCateKey getResCateKey() {
        return resCateKey;
    }

    public void setResCateKey(ResCateKey resCateKey) {
        this.resCateKey = resCateKey;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
