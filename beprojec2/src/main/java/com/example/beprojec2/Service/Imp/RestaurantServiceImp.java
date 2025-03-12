package com.example.beprojec2.Service.Imp;

import com.example.beprojec2.DTO.RestaurantDTO;
import com.example.beprojec2.Entity.Restaurant;
import com.example.beprojec2.Payload.ResponData;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RestaurantServiceImp {
    boolean addRestaurant(String restaurantname,
                          String worktime,
                          MultipartFile image,
                          String address,
                          String description);
    RestaurantDTO getRestaurantById(int restaurantid);

    List<RestaurantDTO> getAllRestaurants();

    ResponData changeRestaurant(int restaurantid,
                                String restaurantname,
                                String worktime,
                                MultipartFile image,
                                String address,
                                String description);
}
