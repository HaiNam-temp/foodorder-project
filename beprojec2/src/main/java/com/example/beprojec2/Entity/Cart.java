package com.example.beprojec2.Entity;

import com.example.beprojec2.Entity.keys.RatingFoodKey;
import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

@Entity(name="cart")
public class Cart {
    @EmbeddedId
    private RatingFoodKey ratingFoodKey;

    @ManyToOne
    @JoinColumn(name="accountid",insertable = false,updatable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name="restaurantid",insertable = false,updatable = false)
    private Restaurant restaurantcart;

    @ManyToOne
    @JoinColumn(name="foodid",insertable = false,updatable = false)
    private Food food;

    @Column(name="quantity")
    private int quantity;

    public RatingFoodKey getRatingFoodKey() {
        return ratingFoodKey;
    }

    public void setRatingFoodKey(RatingFoodKey ratingFoodKey) {
        this.ratingFoodKey = ratingFoodKey;
    }

    public Account getAccountcart() {
        return account;
    }

    public void setAccountcart(Account accountcart) {
        this.account = accountcart;
    }

    public Restaurant getRestaurantcart() {
        return restaurantcart;
    }

    public void setRestaurantcart(Restaurant restaurantcart) {
        this.restaurantcart = restaurantcart;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
