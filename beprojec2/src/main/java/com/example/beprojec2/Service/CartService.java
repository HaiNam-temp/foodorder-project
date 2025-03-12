package com.example.beprojec2.Service;

import com.example.beprojec2.DTO.CartDTO;
import com.example.beprojec2.DTO.CartItemDTO;
import com.example.beprojec2.Entity.Account;
import com.example.beprojec2.Entity.Cart;
import com.example.beprojec2.Entity.Food;
import com.example.beprojec2.Entity.Restaurant;
import com.example.beprojec2.Entity.keys.RatingFoodKey;
import com.example.beprojec2.Exception.ApiException;
import com.example.beprojec2.Payload.Request.CartRequest;
import com.example.beprojec2.Payload.ResponData;
import com.example.beprojec2.Responsitory.AccountReponsitory;
import com.example.beprojec2.Responsitory.CartReponsitory;
import com.example.beprojec2.Responsitory.FoodResponsitory;
import com.example.beprojec2.Responsitory.RestaurantReponsitory;
import com.example.beprojec2.Service.Imp.CartServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService implements CartServiceImp {

    @Autowired
    AccountReponsitory accountReponsitory;
    @Autowired
    CartReponsitory cartReponsitory;
    @Autowired
    private FoodResponsitory foodResponsitory;
    @Autowired
    private RestaurantReponsitory restaurantReponsitory;

    @Override
    public CartDTO getCart(String username) {
        CartDTO cartDTO = new CartDTO();
        Account account = accountReponsitory.findByUsername(username);
        List<Cart> cartList = cartReponsitory.findByAccount(account);
        cartDTO.setFullname(account.getFullname());
        List<CartItemDTO> cartItemDTOS = new ArrayList<>();
        for(Cart cart : cartList) {
            CartItemDTO cartItemDTO = new CartItemDTO();
            cartItemDTO.setFoodname(cart.getFood().getFoodname());
            cartItemDTO.setQuantity(cart.getQuantity());
            cartItemDTO.setId(cart.getFood().getFoodid());
            cartItemDTO.setImage(cart.getFood().getImage());
            cartItemDTO.setPrice(cart.getFood().getPrice());
            cartItemDTO.setRestaurantname(cart.getRestaurantcart().getRestaurantname());
            cartItemDTOS.add(cartItemDTO);
        }
        cartDTO.setCartItems(cartItemDTOS);
        return cartDTO;
    }

    @Override
    public ResponData addFoodToCart(String username, CartRequest cartRequest) {
       ResponData respondata= new ResponData();
        Account account = accountReponsitory.findByUsername(username);
        if(account==null){
            throw ApiException.ErrBadCredentials().build();
        }
        try {
            Cart cart = new Cart();
            Food food = foodResponsitory.findByFoodid(cartRequest.getFoodid());
            Restaurant restaurant = restaurantReponsitory.findByRestaurantid(cartRequest.getRestaurantid());
            RatingFoodKey ratingFoodKey = new RatingFoodKey(cartRequest.getFoodid(),cartRequest.getRestaurantid(),account.getAccountid());

            cart.setRatingFoodKey(ratingFoodKey);
            cart.setFood(food);
            cart.setAccountcart(account);
            cart.setRestaurantcart(restaurant);
            cart.setQuantity(cartRequest.getQuantity());
            cartReponsitory.save(cart);

            respondata.setSuccess(Boolean.TRUE);
            respondata.setExist(false);
        }catch(Exception e) {
            System.out.println("Error insert food to cart "+ e.getMessage());
            respondata.setSuccess(Boolean.FALSE);
            respondata.setExist(true);
        }
        return respondata;
    }

    @Override
    public ResponData removeFoodFromCart(String username, int foodid) {
        ResponData responData = new ResponData();
        Account account = accountReponsitory.findByUsername(username);
        if(account==null){
            throw ApiException.ErrBadCredentials().build();
        }
        try{
            Food food = foodResponsitory.findByFoodid(foodid);
            cartReponsitory.deleteByAccountAndFood(account,food);
            responData.setSuccess(true);
            responData.setExist(false);
        } catch(Exception e){
            responData.setSuccess(Boolean.FALSE);
            responData.setExist(true);
            System.out.println("Error removing food from cart "+ e.getMessage());
        }
        return responData;
    }
}
