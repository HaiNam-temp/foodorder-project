package com.example.beprojec2.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity(name="food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int foodid;

    @Column(name="foodname")
    private String foodname;
    @Column(name="price")
    private double price;
    @Column(name="image")
    private String image;

    @ManyToOne
    @JoinColumn(name="categoryid")
    private Category categoryfood;

    @OneToMany(mappedBy = "food")
    private List<RatingFood> ratingfood;

    @OneToMany(mappedBy = "food")
    private List<OrderDetail> foodorder;

    @OneToMany(mappedBy = "food")
    private List<Cart> cartList;

    public int getFoodid() {
        return foodid;
    }

    public void setFoodid(int foodid) {
        this.foodid = foodid;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Category getCategoryfood() {
        return categoryfood;
    }

    public void setCategoryfood(Category categoryfood) {
        this.categoryfood = categoryfood;
    }

    public List<RatingFood> getRatingfood() {
        return ratingfood;
    }

    public void setRatingfood(List<RatingFood> ratingfood) {
        this.ratingfood = ratingfood;
    }

    public List<OrderDetail> getFoodorder() {
        return foodorder;
    }

    public void setFoodorder(List<OrderDetail> foodorder) {
        this.foodorder = foodorder;
    }

    public List<Cart> getCartList() {
        return cartList;
    }

    public void setCartList(List<Cart> cartList) {
        this.cartList = cartList;
    }
}
