package com.example.beprojec2.Controller;

import com.example.beprojec2.DTO.FoodDTO;
import com.example.beprojec2.DTO.FoodHomeDTO;
import com.example.beprojec2.Payload.Request.FoodRequest;
import com.example.beprojec2.Payload.ResponData;
import com.example.beprojec2.Service.Imp.FoodServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodController {
    @Autowired
    FoodServiceImp foodServiceImp;
    @GetMapping("/home")
    public ResponseEntity<?>getFoodHome() {
        return new ResponseEntity<>(foodServiceImp.getFoodHome(),HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAllFood() {
        return new ResponseEntity(foodServiceImp.getAllFood(), HttpStatus.OK);
    }
    @GetMapping("/detail/{foodid}")
    public ResponseEntity<?> getFoodDetail(@PathVariable int foodid) {
        return new ResponseEntity<>(foodServiceImp.getFoodDetailById(foodid),HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponData addFood(@RequestBody FoodRequest foodRequest) {
        MultipartFile image=foodRequest.getImage();
        int categoryid=foodRequest.getCategoryid();
        String foodname=foodRequest.getFoodname();
        double price=foodRequest.getPrice();

        return foodServiceImp.insertFood(image, foodname, categoryid, price);
    }
    @DeleteMapping("/delete")
    public ResponData deleteFood(@RequestParam int foodid) {
        return foodServiceImp.deleteFood(foodid);
    }
    @GetMapping("/foodbyCategory")
    public ResponseEntity<?> getFoodByCategory(@RequestParam int categoryid) {
        return new ResponseEntity<>(foodServiceImp.getFoodByCategory(categoryid),HttpStatus.OK);
    }
}
