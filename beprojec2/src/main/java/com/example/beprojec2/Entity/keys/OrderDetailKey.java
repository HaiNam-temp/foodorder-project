package com.example.beprojec2.Entity.keys;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class OrderDetailKey implements Serializable {
    private int orderid;
    private int foodid;
    public OrderDetailKey() {}
    public OrderDetailKey(int orderid, int foodid) {
        this.orderid = orderid;
        this.foodid = foodid;
    }
    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getFoodid() {
        return foodid;
    }

    public void setFoodid(int foodid) {
        this.foodid = foodid;
    }
}
