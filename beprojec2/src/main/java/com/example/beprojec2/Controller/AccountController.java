package com.example.beprojec2.Controller;

import com.example.beprojec2.Entity.Account;
import com.example.beprojec2.Entity.Cart;
import com.example.beprojec2.Exception.ApiException;
import com.example.beprojec2.Payload.Request.CartRequest;
import com.example.beprojec2.Payload.ResponData;
import com.example.beprojec2.Responsitory.AccountReponsitory;
import com.example.beprojec2.Service.AccountService;
import com.example.beprojec2.Service.Imp.AccountServiceImp;
import com.example.beprojec2.util.JwtUtilHelper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/user")
public class AccountController {

    @Autowired
    AccountServiceImp accountServiceimp;

    @Autowired
    AccountReponsitory AccountReponsitory;

    @Autowired
    JwtUtilHelper jwtUtil;
    // lấy token từ header
    private String getTokenFromHeader(WebRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
    @GetMapping("/me")
    public ResponseEntity<?> currentuserdetail(WebRequest request) {
        // lấy token từ header rồi nhận về username sau đó truy vấn database
        String token = getTokenFromHeader(request);
        if (token == null) {
           throw ApiException.ErrUnauthorized().build();
        }
            String username = jwtUtil.getUsername(token);
            return new ResponseEntity<>(accountServiceimp.accountDetails(username),  HttpStatus.OK);
    }

    @PostMapping("/forgot/code")
    public ResponseEntity<?> sendCode(@RequestParam String username) {
        return new ResponseEntity<>(accountServiceimp.sendVerifyCode(username), HttpStatus.OK);
    }

    @PutMapping("/forgot/passwork")
    public ResponseEntity<?> resetPasswork(@RequestParam String username, @RequestParam String code,@RequestParam String newpassword) {
        return new ResponseEntity<>(accountServiceimp.resetPassword(username,code,newpassword), HttpStatus.OK);
    }

}
