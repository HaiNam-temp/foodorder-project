package com.example.beprojec2.Entity;

import com.example.beprojec2.Entity.keys.OrderDetailKey;
import jakarta.persistence.*;

@Entity(name="orderdetail")
public class OrderDetail {
    @EmbeddedId
    private OrderDetailKey orderDetailKey;
    // quan hệ 1 nhiều với bảng order
    @ManyToOne
    @JoinColumn(name="orderid",insertable = false,updatable = false)// không tạo mới column
    private Order order;

    // quan hệ 1 nhiều với bảng food
    @ManyToOne
    @JoinColumn(name="foodid",insertable = false,updatable = false)
    private Food food;

    @Column(name="quantity")
    private int quantity;

    public OrderDetailKey getOrderDetailKey() {
        return orderDetailKey;
    }

    public void setOrderDetailKey(OrderDetailKey orderDetailKey) {
        this.orderDetailKey = orderDetailKey;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}
