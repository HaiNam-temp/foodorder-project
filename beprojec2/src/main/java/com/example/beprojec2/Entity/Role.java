package com.example.beprojec2.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity(name="role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleid;
    @Column(name="rolename")
    private String rolename;

    @OneToMany(mappedBy = "role",fetch=FetchType.LAZY)
    private List<Account> accounts;

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
