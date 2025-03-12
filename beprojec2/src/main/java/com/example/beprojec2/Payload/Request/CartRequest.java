package com.example.beprojec2.Payload.Request;

public class CartRequest {
    private int foodid;
    private int restaurantid;
    private int quantity;

    public int getRestaurantid() {
        return restaurantid;
    }

    public void setRestaurantid(int restaurantid) {
        this.restaurantid = restaurantid;
    }

    public int getFoodid() {
        return foodid;
    }

    public void setProductId(int foodid) {
        this.foodid = foodid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
