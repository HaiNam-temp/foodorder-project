package com.example.beprojec2.Responsitory;

import com.example.beprojec2.Entity.Account;
import com.example.beprojec2.Entity.Cart;
import com.example.beprojec2.Entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public interface CartReponsitory extends JpaRepository<Cart, Integer> {
    List<Cart> findByAccount(Account account);
    Cart findByAccountAndFood(Account account, Food food);
    @Transactional
    void deleteByAccountAndFood(Account  account, Food food);
}
