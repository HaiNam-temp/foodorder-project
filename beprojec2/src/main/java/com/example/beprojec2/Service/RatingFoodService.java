package com.example.beprojec2.Service;

import com.example.beprojec2.DTO.RatingDTO;
import com.example.beprojec2.Entity.Account;
import com.example.beprojec2.Entity.Food;
import com.example.beprojec2.Entity.RatingFood;
import com.example.beprojec2.Entity.Restaurant;
import com.example.beprojec2.Entity.keys.RatingFoodKey;
import com.example.beprojec2.Payload.ResponData;
import com.example.beprojec2.Responsitory.AccountReponsitory;
import com.example.beprojec2.Responsitory.FoodResponsitory;
import com.example.beprojec2.Responsitory.RatingFoodReponsitory;
import com.example.beprojec2.Responsitory.RestaurantReponsitory;
import com.example.beprojec2.Service.Imp.RatingFoodServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class RatingFoodService implements RatingFoodServiceImp {
    @Autowired
    AccountReponsitory accountReponsitory;
    @Autowired
    RatingFoodReponsitory ratingFoodReponsitory;
    @Autowired
    FileService fileService;
    @Autowired
    FoodResponsitory foodResponsitory;
    @Autowired
    private RestaurantReponsitory restaurantReponsitory;

    @Override
    public List<RatingDTO> getallRatingFood(String username) {
        Account account=accountReponsitory.findByUsername(username);
        List<RatingFood> ratingFoodList=ratingFoodReponsitory.findByAccount(account);
        List<RatingDTO> ratingDTOList=new ArrayList<>();
        for(RatingFood ratingFood:ratingFoodList){

            RatingDTO ratingDTO=new RatingDTO();
            ratingDTO.setFoodid(ratingFood.getFood().getFoodid());
            ratingDTO.setStar(ratingFood.getStar());
            ratingDTO.setContent(ratingFood.getContent());
            ratingDTO.setFullname(account.getFullname());
            ratingDTO.setRestaurant(ratingFood.getRestaurant().getRestaurantname());
            ratingDTO.setImage(ratingFood.getImage());
            ratingDTOList.add(ratingDTO);
        }
        return ratingDTOList;
    }

    @Override
    public ResponData deleteRatingFood(String username, int foodid) {
        ResponData responData=new ResponData();
        Account account=accountReponsitory.findByUsername(username);
        Food food=foodResponsitory.findById(foodid).get();
        RatingFood ratingFood= ratingFoodReponsitory.findByAccountAndFood(account,food);
        try{
            if(fileService.deleteFile(ratingFood.getImage())){
                ratingFoodReponsitory.delete(ratingFood);
                responData.setSuccess(true);
                responData.setExist(false);
            }
            responData.setSuccess(false);
            responData.setExist(false);
        }catch(Exception e){
            responData.setSuccess(false);
            responData.setExist(true);
            System.out.println("Error delete ratingfood "+ e.getMessage());
        }
        return responData;
    }

    @Override
    public ResponData insertRatingFood(String username, int foodid, int restaurantid,int star, MultipartFile image, String content) {
        ResponData responData=new ResponData();
        Account account=accountReponsitory.findByUsername(username);
        Food food=foodResponsitory.findById(foodid).get();
        Restaurant restaurant=restaurantReponsitory.findByRestaurantid(restaurantid);
        RatingFoodKey ratingFoodKey=new RatingFoodKey(account.getAccountid(),foodid,restaurantid);
        try{
            if(fileService.saveFile(image)){
                RatingFood ratingFood=new RatingFood();
                ratingFood.setStar(star);
                ratingFood.setContent(content);
                ratingFood.setRatingFoodKey(ratingFoodKey);
                ratingFood.setImage(image.getOriginalFilename());
                ratingFood.setAccount(account);
                ratingFood.setFood(food);
                ratingFood.setRestaurant(restaurant);
                ratingFoodReponsitory.save(ratingFood);
                responData.setExist(false);
                responData.setSuccess(true);
            }
            responData.setSuccess(false);
            responData.setExist(false);
        } catch (Exception e) {
            responData.setSuccess(false);
            responData.setExist(true);
            System.out.println("Error inserting ratingfood "+ e.getMessage());
        }
        return responData;
    }
}
