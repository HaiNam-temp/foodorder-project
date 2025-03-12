package com.example.beprojec2.Responsitory;

import com.example.beprojec2.Entity.Account;
import com.example.beprojec2.Entity.Food;
import com.example.beprojec2.Entity.RatingFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingFoodReponsitory extends JpaRepository<RatingFood, Integer> {
    List<RatingFood> findByAccount(Account account);
    RatingFood findByAccountAndFood(Account account, Food food);

}
