package com.example.beprojec2.Entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity(name="order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderid;
    @Column(name="orderdate")
    private Date orderdate;
    @Column(name="status")
    private String status;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderdetail;

    @ManyToOne
    @JoinColumn(name="accountid",insertable = false,updatable = false)
    private Account account;

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderDetail> getOrderdetail() {
        return orderdetail;
    }

    public void setOrderdetail(List<OrderDetail> orderdetail) {
        this.orderdetail = orderdetail;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
