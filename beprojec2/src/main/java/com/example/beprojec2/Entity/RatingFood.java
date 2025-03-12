package com.example.beprojec2.Entity;

import com.example.beprojec2.Entity.keys.RatingFoodKey;
import jakarta.persistence.*;

@Entity(name="ratingfood")
public class RatingFood {
    @EmbeddedId
    private RatingFoodKey ratingFoodKey;

    @ManyToOne
    @JoinColumn(name="restaurantid",insertable = false,updatable = false)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name="foodid",insertable = false,updatable = false)
    private Food food;

    @ManyToOne
    @JoinColumn(name="accountid",insertable = false,updatable = false)
    private Account account;

    @Column(name="star")
    private int star;
    @Column(name="content")
    private String content;
    @Column(name="image")
    private String image;

    public RatingFoodKey getRatingFoodKey() {
        return ratingFoodKey;
    }

    public void setRatingFoodKey(RatingFoodKey ratingFoodKey) {
        this.ratingFoodKey = ratingFoodKey;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
