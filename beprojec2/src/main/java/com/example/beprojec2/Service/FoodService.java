package com.example.beprojec2.Service;

import com.example.beprojec2.DTO.FoodDTO;
import com.example.beprojec2.DTO.FoodHomeDTO;
import com.example.beprojec2.DTO.RatingDTO;
import com.example.beprojec2.Entity.Category;
import com.example.beprojec2.Entity.Food;
import com.example.beprojec2.Entity.RatingFood;
import com.example.beprojec2.Exception.ApiException;
import com.example.beprojec2.Payload.ResponData;
import com.example.beprojec2.Responsitory.CategoryReponsitory;
import com.example.beprojec2.Responsitory.FoodResponsitory;
import com.example.beprojec2.Service.Imp.FileServiceImp;
import com.example.beprojec2.Service.Imp.FoodServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
@Service
public class FoodService implements FoodServiceImp {
    @Autowired
    FoodResponsitory foodResponsitory;
    @Autowired
    FileServiceImp fileServiceimp;
    @Autowired
    CategoryReponsitory categoryReponsitory;

    public boolean isSaveFileSuccess(MultipartFile file){
        return fileServiceimp.saveFile(file);
    }
    @Override
    public List<FoodHomeDTO> getFoodByCategory(int categoryid) {
        Category category= categoryReponsitory.findById(categoryid).get();
        List<FoodHomeDTO> foodHomeDTOList= new ArrayList<>();
        for(Food food: category.getFoods()){
            FoodHomeDTO foodHomeDTO = new FoodHomeDTO();
            foodHomeDTO.setFoodid(food.getFoodid());
            foodHomeDTO.setFoodname(food.getFoodname());
            foodHomeDTO.setPrice(food.getPrice());
            foodHomeDTO.setCategory(category.getCategoryname());
            foodHomeDTO.setImage(food.getImage());
            foodHomeDTOList.add(foodHomeDTO);
        }
        return foodHomeDTOList;
    }

    @Override
    public List<FoodHomeDTO> getFoodHome() {
        PageRequest pageRequest = PageRequest.of(0, 2);
        Page<Food> foodList = foodResponsitory.findAll(pageRequest);
        List<FoodHomeDTO> foodHomeDTOList = new ArrayList<>();
        for (Food food : foodList) {
            FoodHomeDTO foodHomeDTO = new FoodHomeDTO();
            foodHomeDTO.setFoodid(food.getFoodid());
            foodHomeDTO.setFoodname(food.getFoodname());
            foodHomeDTO.setCategory(food.getCategoryfood().getCategoryname());
            foodHomeDTO.setImage(food.getImage());
            foodHomeDTO.setPrice(food.getPrice());
            foodHomeDTOList.add(foodHomeDTO);
        }
        return foodHomeDTOList;
    }

    @Override
    public ResponData deleteFood(int foodid) {
        ResponData responData = new ResponData();

        return responData;
    }


    @Override
    public ResponData insertFood(MultipartFile image,
                                 String foodname,
                                 int categoryid,
                                 double price) {
        ResponData responData = new ResponData();
        try{
            if(isSaveFileSuccess(image)){
                Food food = new Food();
                Category category=categoryReponsitory.findById(categoryid).get();
                food.setFoodname(foodname);
                food.setCategoryfood(category);
                food.setImage(image.getOriginalFilename());
                food.setPrice(price);
                foodResponsitory.save(food);
                responData.setSuccess(true);
                responData.setExist(false);

            }
        }catch (Exception e){
            responData.setSuccess(false);
            responData.setExist(true);
            System.out.println("Error insert Food "+ e.getMessage());
        }
        return responData;
    }

    @Override
    public List<FoodHomeDTO> getAllFood() {
        List<Food> foodList = foodResponsitory.findAll();
        List<FoodHomeDTO> foodHomeDTOList = new ArrayList<>();
        for (Food food : foodList) {
            FoodHomeDTO foodHomeDTO = new FoodHomeDTO();
            foodHomeDTO.setFoodid(food.getFoodid());
            foodHomeDTO.setFoodname(food.getFoodname());
            foodHomeDTO.setCategory(food.getCategoryfood().getCategoryname());
            foodHomeDTO.setImage(food.getImage());
            foodHomeDTO.setPrice(food.getPrice());
            foodHomeDTOList.add(foodHomeDTO);
        }
        return foodHomeDTOList;
    }

    @Override
    public FoodDTO getFoodDetailById(int foodid) {
        Food food = foodResponsitory.findById(foodid).get();
        if(food==null){
            throw ApiException.ErrDataLoss().build();
        }
        FoodDTO foodDTO = new FoodDTO();
        foodDTO.setFoodid(food.getFoodid());
        foodDTO.setFoodname(food.getFoodname());
        foodDTO.setCategory(food.getCategoryfood().getCategoryname());
        foodDTO.setImage(food.getImage());
        foodDTO.setPrice(food.getPrice());
        List<RatingDTO> ratingDTOList=new ArrayList<>();
        for(RatingFood ratingFood : food.getRatingfood()){
            RatingDTO ratingDTO = new RatingDTO();
            ratingDTO.setFoodid(ratingFood.getFood().getFoodid());
            ratingDTO.setFullname(ratingFood.getAccount().getFullname());
            ratingDTO.setContent(ratingFood.getContent());
            ratingDTO.setImage(ratingFood.getImage());
            ratingDTO.setRestaurant(ratingFood.getRestaurant().getRestaurantname());
            ratingDTO.setStar(ratingFood.getStar());
            ratingDTOList.add(ratingDTO);
        }
        foodDTO.setRatingFoodDTOList(ratingDTOList);
        return foodDTO;
    }
}
