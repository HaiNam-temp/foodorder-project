package com.example.beprojec2.Entity;

import com.example.beprojec2.Entity.keys.RatingRestaurantKey;
import jakarta.persistence.*;

import java.util.Date;

@Entity(name="ratingrestaurant")
public class RatingRestaurant {
    @EmbeddedId
    private RatingRestaurantKey ratingRestaurantKey;

    @ManyToOne
    @JoinColumn(name="restaurantid",insertable = false,updatable = false)
    private Restaurant restaurantrating;

    @ManyToOne
    @JoinColumn(name="accountid",insertable = false,updatable = false)
    private Account account;

    @Column(name="createdate")
    @Temporal(TemporalType.DATE)
    private Date createdate;

    @Column(name="content")
    private String content;
    @Column(name="star")
    private int star;

    public RatingRestaurantKey getRatingRestaurantKey() {
        return ratingRestaurantKey;
    }

    public void setRatingRestaurantKey(RatingRestaurantKey ratingRestaurantKey) {
        this.ratingRestaurantKey = ratingRestaurantKey;
    }

    public Restaurant getRestaurantrating() {
        return restaurantrating;
    }

    public void setRestaurantrating(Restaurant restaurantrating) {
        this.restaurantrating = restaurantrating;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }
}
