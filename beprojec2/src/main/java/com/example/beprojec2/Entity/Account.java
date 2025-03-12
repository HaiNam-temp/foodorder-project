package com.example.beprojec2.Entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity(name="account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // tự động tăng
    private int accountid;
    @Column(name="fullname")
    private String fullname;
    @Column(name="password")
    private String password;
    @Column(name="username")
    private String username;
    @Column(name="startdate")
    @Temporal(TemporalType.DATE)
    private Date startdate;
    @Column(name="code")
    private String code;
    @Column(name="codecreatedate")
    private Timestamp codecreatedate;
    //mối quan hệ  nhiều-1 với roles
    @ManyToOne
    @JoinColumn(name="roleid")
    private Role role;

    @OneToMany(mappedBy = "account")
    private List<RatingFood> accoungRatingFoods;

    @OneToMany(mappedBy = "account")
    private List<Order> accountOrders;
    @OneToMany(mappedBy = "account")
    private List<RatingRestaurant> accountRatingRes;

    @OneToMany(mappedBy = "account")
    private List<Cart> accountCarts;

    public List<Cart> getAccountCarts() {
        return accountCarts;
    }

    public void setAccountCarts(List<Cart> accountCarts) {
        this.accountCarts = accountCarts;
    }

    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<RatingFood> getAccoungRatingFoods() {
        return accoungRatingFoods;
    }

    public void setAccoungRatingFoods(List<RatingFood> accoungRatingFoods) {
        this.accoungRatingFoods = accoungRatingFoods;
    }

    public List<Order> getAccountOrders() {
        return accountOrders;
    }

    public void setAccountOrders(List<Order> accountOrders) {
        this.accountOrders = accountOrders;
    }

    public List<RatingRestaurant> getAccountRatingRes() {
        return accountRatingRes;
    }

    public void setAccountRatingRes(List<RatingRestaurant> accountRatingRes) {
        this.accountRatingRes = accountRatingRes;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Timestamp getCodecreatedate() {
        return codecreatedate;
    }

    public void setCodecreatedate(Timestamp codecreatedate) {
        this.codecreatedate = codecreatedate;
    }
}
