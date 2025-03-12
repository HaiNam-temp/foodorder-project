package com.example.beprojec2.DTO;

import java.util.List;

public class CartDTO {
    String fullname;
    List<CartItemDTO> cartItems;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public List<CartItemDTO> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemDTO> cartItems) {
        this.cartItems = cartItems;
    }
}
