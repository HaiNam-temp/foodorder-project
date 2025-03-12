package com.example.beprojec2.Controller;

import com.example.beprojec2.Exception.ApiException;
import com.example.beprojec2.Payload.Request.CartRequest;
import com.example.beprojec2.Service.Imp.AccountServiceImp;
import com.example.beprojec2.Service.Imp.CartServiceImp;
import com.example.beprojec2.util.JwtUtilHelper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    JwtUtilHelper jwtUtil;
    @Autowired
    AccountServiceImp accountServiceimp;
    @Autowired
    CartServiceImp cartServiceimp;

    private String getTokenFromHeader(WebRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
    @GetMapping("")
    public ResponseEntity<?> getCart(WebRequest request) {

        String token = getTokenFromHeader(request);
        if (token == null) {
            throw ApiException.ErrUnauthorized().build();
        }
        String username=jwtUtil.getUsername(token);
        return new ResponseEntity<>(cartServiceimp.getCart(username), HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<?> addFoodToCart(WebRequest request, @RequestBody CartRequest cartRequest) {
        String token = getTokenFromHeader(request);
        String username=jwtUtil.getUsername(token);
        System.out.println(cartRequest.getFoodid() + " " +cartRequest.getRestaurantid() + " "+cartRequest.getQuantity());
        return new ResponseEntity<>(cartServiceimp.addFoodToCart(username, cartRequest), HttpStatus.OK);
    }
    @DeleteMapping("{foodid}")
    public ResponseEntity<?> removeFoodFromCart(WebRequest request, @PathVariable int foodid) {
        String token = getTokenFromHeader(request);
        String username=jwtUtil.getUsername(token);
        return new ResponseEntity<>(cartServiceimp.removeFoodFromCart(username, foodid), HttpStatus.OK);
    }
}
