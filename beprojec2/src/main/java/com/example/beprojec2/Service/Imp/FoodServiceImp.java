package com.example.beprojec2.Service.Imp;

import com.example.beprojec2.DTO.FoodDTO;
import com.example.beprojec2.DTO.FoodHomeDTO;
import com.example.beprojec2.Payload.ResponData;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FoodServiceImp {
    List<FoodHomeDTO> getFoodHome();
    FoodDTO getFoodDetailById(int foodid);
    List<FoodHomeDTO> getAllFood();
    ResponData insertFood(MultipartFile image,
                           String foodname,
                           int categoryid,
                           double price);
    ResponData deleteFood(int foodid);
    List<FoodHomeDTO>getFoodByCategory(int categoryid);
}
