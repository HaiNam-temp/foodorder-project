package com.example.beprojec2.Controller;

import com.example.beprojec2.DTO.RestaurantDTO;
import com.example.beprojec2.Payload.ResponData;
import com.example.beprojec2.Service.Imp.RestaurantServiceImp;
import com.example.beprojec2.Service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    RestaurantServiceImp restaurantServiceImp;
    @PostMapping("/create")
    public ResponseEntity<?> createRestaurant(@RequestParam String restaurantname,
                                              @RequestParam String worktime,
                                              @RequestParam MultipartFile image,
                                              @RequestParam String address,
                                              @RequestParam String description
                                              ) {
        ResponData response = new ResponData();
        boolean isSuccess=restaurantServiceImp.addRestaurant(restaurantname, worktime, image, address, description);
        response.setSuccess(isSuccess);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("")
    public ResponseEntity<?> getAllRestaurants() {
        return null;
    }
    @GetMapping("/detailRestaurant")
    public RestaurantDTO getRestaurant(@RequestParam int restaurantid) {
        return restaurantServiceImp.getRestaurantById(restaurantid);
    }
    @PutMapping("/change")
    public  ResponseEntity<?> changeRestaurant(@RequestParam int restaurantid,
                                               @RequestParam String restaurantname,
                                               @RequestParam String worktime,
                                               @RequestParam MultipartFile image,
                                               @RequestParam String address,
                                               @RequestParam String description){
        ResponData response = restaurantServiceImp.changeRestaurant(restaurantid, restaurantname, worktime, image, address, description);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
