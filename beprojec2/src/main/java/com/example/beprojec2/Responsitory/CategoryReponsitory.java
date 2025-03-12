package com.example.beprojec2.Responsitory;

import com.example.beprojec2.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryReponsitory extends JpaRepository<Category, Integer> {

}
