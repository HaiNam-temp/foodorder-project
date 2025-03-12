package com.example.beprojec2.Controller;

import com.example.beprojec2.Entity.Account;
import com.example.beprojec2.Exception.ApiException;
import com.example.beprojec2.Payload.Request.OrderRequest;
import com.example.beprojec2.Responsitory.AccountReponsitory;
import com.example.beprojec2.Service.OrderService;
import com.example.beprojec2.util.JwtUtilHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    JwtUtilHelper jwtUtil;
    @Autowired
    OrderService orderService;
    @Autowired
    AccountReponsitory accountReponsitory;
    // lấy token từ header
    private String getTokenFromHeader(WebRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
    @PostMapping("")
    public ResponseEntity<?> createOrder(WebRequest request, @RequestBody OrderRequest orderRequest) {
        String token=getTokenFromHeader(request);
        if(token==null){
            ApiException.ErrUnauthorized();
        }
        String username=getTokenFromHeader(request);
        return new ResponseEntity<>(orderService.createOrder(username,orderRequest), HttpStatus.OK);
    }
    @DeleteMapping("")
    public ResponseEntity<?> deleteOrder(WebRequest request, int orderId) {
        String token=getTokenFromHeader(request);
        if(token==null){
            ApiException.ErrUnauthorized();
        }
        String username=getTokenFromHeader(request);
        Account account= accountReponsitory.findByUsername(username);
        if(account==null){
            ApiException.ErrBadCredentials().build();
        }
        return new ResponseEntity<>(orderService.deleteOrder(orderId),HttpStatus.OK);
    }
}
