package com.example.beprojec2.Security;

import com.example.beprojec2.Entity.Account;
import com.example.beprojec2.Responsitory.AccountReponsitory;
import com.example.beprojec2.Service.AccountService;
import org.hibernate.sql.ast.tree.expression.Over;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    AccountReponsitory accountReponsitory;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("kiemtra"+username);
        Account account = accountReponsitory.findByUsername(username);
        System.out.println("kiemtra"+username);
        if (account == null) {
            throw new UsernameNotFoundException(username+"User not found");

        }
        System.out.println("kiemtra"+account.getUsername());
        return new User(username,account.getPassword(),new ArrayList<>());
    }

}
