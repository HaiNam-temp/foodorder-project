package com.example.beprojec2.Responsitory;

import com.example.beprojec2.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderReponsitory extends JpaRepository<Order, Integer> {
}
