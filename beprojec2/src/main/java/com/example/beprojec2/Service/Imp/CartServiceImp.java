package com.example.beprojec2.Service.Imp;

import com.example.beprojec2.DTO.CartDTO;
import com.example.beprojec2.Payload.Request.CartRequest;
import com.example.beprojec2.Payload.ResponData;

public interface CartServiceImp {
    CartDTO getCart(String username);
    ResponData addFoodToCart(String username, CartRequest cartRequest);
    ResponData removeFoodFromCart(String username, int foodid);
}
