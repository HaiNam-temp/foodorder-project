package com.example.beprojec2.Service.Imp;

import com.example.beprojec2.DTO.RatingDTO;
import com.example.beprojec2.Entity.RatingFood;
import com.example.beprojec2.Payload.ResponData;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RatingFoodServiceImp {
    ResponData insertRatingFood(String username,
                                int foodid,
                                int restaurantid,
                                int star,
                                MultipartFile image,
                                String content);
    ResponData deleteRatingFood(String username,
                                int foodid);
    List<RatingDTO> getallRatingFood(String username);

}
