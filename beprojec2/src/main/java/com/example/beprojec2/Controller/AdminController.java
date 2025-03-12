package com.example.beprojec2.Controller;

import com.example.beprojec2.Service.Imp.AccountServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController// Rest Controller bao gồm tất cả chức năng của controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AccountServiceImp accountImp;
//    @GetMapping("/alluser")
//    public ResponseEntity<?> getAllUsers() {
//        return new ResponseEntity<>(accountImp.getallAccounts(), HttpStatus.OK);
//    }
}
