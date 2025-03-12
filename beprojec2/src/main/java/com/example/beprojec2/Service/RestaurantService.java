package com.example.beprojec2.Service;

import com.example.beprojec2.DTO.CategoryDTO;
import com.example.beprojec2.DTO.FoodHomeDTO;
import com.example.beprojec2.DTO.RestaurantDTO;
import com.example.beprojec2.Entity.Category;
import com.example.beprojec2.Entity.Food;
import com.example.beprojec2.Entity.Restaurant;
import com.example.beprojec2.Entity.RestaurantCategory;
import com.example.beprojec2.Payload.ResponData;
import com.example.beprojec2.Responsitory.CategoryReponsitory;
import com.example.beprojec2.Responsitory.RestaurantReponsitory;
import com.example.beprojec2.Service.Imp.RestaurantServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService implements RestaurantServiceImp {
    @Autowired
    RestaurantReponsitory restaurantReponsitory;

    @Autowired
    FileService fileService;
    @Autowired
    private CategoryReponsitory categoryReponsitory;

    public boolean isSaveFileSuccess(MultipartFile file){
        return fileService.saveFile(file);
    }
    @Override
    public boolean addRestaurant(String restaurantname, String worktime, MultipartFile image, String address, String description) {
        boolean successAddRestaurant = false;
        try{
        if (isSaveFileSuccess(image)) {
            Restaurant restaurant = new Restaurant();
            restaurant.setRestaurantname(restaurantname);
            restaurant.setWorktime(worktime);
            restaurant.setAddress(address);
            restaurant.setDescription(description);
            restaurant.setImage(image.getOriginalFilename());
            restaurantReponsitory.save(restaurant);
                 }
            successAddRestaurant = true;
            } catch (Exception e) {
                System.out.println("Error add Restaurant " + e.getMessage());
               successAddRestaurant = false;

        }
        return successAddRestaurant;
    }

    @Override
    public ResponData changeRestaurant(int restaurantid,String restaurantname, String worktime, MultipartFile image, String address, String description) {
        ResponData responData = new ResponData();
        try{
            if (isSaveFileSuccess(image)) {
                Restaurant restaurant = restaurantReponsitory.findByRestaurantid(restaurantid);
                restaurant.setRestaurantname(restaurantname);
                restaurant.setWorktime(worktime);
                restaurant.setAddress(address);
                restaurant.setDescription(description);
                restaurant.setImage(image.getOriginalFilename());
                restaurantReponsitory.save(restaurant);
            }
            responData.setSuccess(true);
            responData.setExist(false);
        } catch (Exception e) {
            responData.setSuccess(false);
            responData.setExist(true);
            System.out.println("Error change Restaurant " + e.getMessage());
        }
        return responData;
    }

    @Override
    public List<RestaurantDTO> getAllRestaurants() {
        return List.of();
    }

    @Override
    public RestaurantDTO getRestaurantById(int restaurantid) {
        Restaurant restaurant = restaurantReponsitory.findById(restaurantid).get();
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setRestaurantid(restaurantid);
        restaurantDTO.setRestaurantname(restaurant.getRestaurantname());
        restaurantDTO.setWorktime(restaurant.getWorktime());
        restaurantDTO.setAddress(restaurant.getAddress());
        restaurantDTO.setDescription(restaurant.getDescription());
        restaurantDTO.setImage(restaurant.getImage());

        // lấy menu cho restaurant
        List<CategoryDTO> categoryDTOList=new ArrayList<>();
        for(RestaurantCategory restaurantCategory: restaurant.getRestaurantCategory()){

            CategoryDTO categoryDTO= new CategoryDTO();
            Category category = restaurantCategory.getCategory();
            categoryDTO.setCategoryid(category.getCategoryid());
            categoryDTO.setCategoryname(category.getCategoryname());

            // lấy danh sách đồ ăn trong menu
            List<FoodHomeDTO> foodHomeDTOList=new ArrayList<>();
            for(Food food :category.getFoods()){
                FoodHomeDTO foodHomeDTO= new FoodHomeDTO();
                foodHomeDTO.setFoodid(food.getFoodid());
                foodHomeDTO.setFoodname(food.getFoodname());
                foodHomeDTO.setPrice(food.getPrice());
                foodHomeDTO.setImage(food.getImage());
                foodHomeDTO.setCategory(category.getCategoryname());
                foodHomeDTOList.add(foodHomeDTO);
            }

            categoryDTO.setFoodlist(foodHomeDTOList);
            categoryDTOList.add(categoryDTO);
        }
        restaurantDTO.setCategoryDTOS(categoryDTOList);
        return restaurantDTO;
    }
}
