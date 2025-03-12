package com.example.beprojec2.Controller;

import com.example.beprojec2.DTO.RatingDTO;
import com.example.beprojec2.Entity.RatingFood;
import com.example.beprojec2.Payload.ResponData;
import com.example.beprojec2.Service.Imp.RatingFoodServiceImp;
import com.example.beprojec2.util.JwtUtilHelper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingFoodController {
    @Autowired
    RatingFoodServiceImp ratingFoodServiceImp;
    @Autowired
    JwtUtilHelper jwtUtilHelper;
    private String getTokenFromHeader(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
    @DeleteMapping("{foodid}")
    public ResponData deleteRating(HttpServletRequest request, @PathVariable int foodid) {
        String token = getTokenFromHeader(request);
        String username=jwtUtilHelper.getUsername(token);
        return ratingFoodServiceImp.deleteRatingFood(username,foodid);
    }
    @PostMapping("")
    public ResponData addRatingFood(HttpServletRequest request,
                                    @RequestParam String username,
                                    @RequestParam int foodid,
                                    @RequestParam int restaurantid,
                                    @RequestParam int star,
                                    @RequestParam MultipartFile image,
                                    @RequestParam String content) {
        return ratingFoodServiceImp.insertRatingFood(username,foodid,restaurantid,star,image,content);
    }
    @GetMapping("/allRating")
    public ResponseEntity<?> getAllRating(HttpServletRequest request) {
        String token = getTokenFromHeader(request);
        String username=jwtUtilHelper.getUsername(token);
        return new ResponseEntity<>(ratingFoodServiceImp.getallRatingFood(username),HttpStatus.OK);
    }
}
