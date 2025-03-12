package com.example.beprojec2.Responsitory;

import com.example.beprojec2.Entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantReponsitory extends JpaRepository<Restaurant, Integer> {
    Restaurant findByRestaurantid(int restaurantId);
}
