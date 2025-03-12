package com.example.beprojec2.Entity;

import jakarta.persistence.*;

import java.awt.*;
import java.util.List;

@Entity(name="restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int restaurantid;
    @Column(name="restaurantname")
    private String restaurantname;
    @Column(name="worktime")
    private String worktime;
    @Column(name="address")
    private String address;
    @Column(name="image")
    private String image;
    @Column(name="description")
    private String description;

    @OneToMany(mappedBy = "restaurant")
    private List<RatingFood> resRatingFood;

    @OneToMany(mappedBy = "restaurant")
    private List<RestaurantCategory> restaurantCategory;

    @OneToMany(mappedBy = "restaurantrating")
    private List<RatingRestaurant> ratingRestaurants;

    @OneToMany(mappedBy = "restaurantcart")
    private List<Cart> restaurantCarts;

    public String getImage() {
        return image;
    }

    public List<Cart> getRestaurantCarts() {
        return restaurantCarts;
    }

    public void setRestaurantCarts(List<Cart> restaurantCarts) {
        this.restaurantCarts = restaurantCarts;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRestaurantid() {
        return restaurantid;
    }

    public void setRestaurantid(int restaurantid) {
        this.restaurantid = restaurantid;
    }

    public String getRestaurantname() {
        return restaurantname;
    }

    public void setRestaurantname(String restaurantname) {
        this.restaurantname = restaurantname;
    }

    public String getWorktime() {
        return worktime;
    }

    public void setWorktime(String worktime) {
        this.worktime = worktime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<RatingFood> getResRatingFood() {
        return resRatingFood;
    }

    public void setResRatingFood(List<RatingFood> resRatingFood) {
        this.resRatingFood = resRatingFood;
    }

    public List<RestaurantCategory> getRestaurantCategory() {
        return restaurantCategory;
    }

    public void setRestaurantCategory(List<RestaurantCategory> restaurantCategory) {
        this.restaurantCategory = restaurantCategory;
    }

    public List<RatingRestaurant> getRatingRestaurants() {
        return ratingRestaurants;
    }

    public void setRatingRestaurants(List<RatingRestaurant> ratingRestaurants) {
        this.ratingRestaurants = ratingRestaurants;
    }
}
