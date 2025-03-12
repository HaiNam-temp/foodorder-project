package com.example.beprojec2.Responsitory;

import com.example.beprojec2.DTO.AccountDTO;
import com.example.beprojec2.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountReponsitory extends JpaRepository<Account,Integer> {
    List<Account> findAll();
    boolean existsByUsername(String username);
    Account findByUsername(String username);
}
