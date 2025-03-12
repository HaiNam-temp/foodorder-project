package com.example.beprojec2.Responsitory;

import com.example.beprojec2.Entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodResponsitory extends JpaRepository<Food, Integer> {
    Food findByFoodid(int foodid);
}
