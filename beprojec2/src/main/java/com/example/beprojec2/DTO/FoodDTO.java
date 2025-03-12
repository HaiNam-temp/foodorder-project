package com.example.beprojec2.DTO;

import java.util.List;

public class FoodDTO {
    private int foodid;
    private String foodname;
    private String image;
    private String category;
    private double price;
    private List<RatingDTO> ratingFoodDTOList;

    public int getFoodid() {
        return foodid;
    }

    public void setFoodid(int foodid) {
        this.foodid = foodid;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<RatingDTO> getRatingFoodDTOList() {
        return ratingFoodDTOList;
    }

    public void setRatingFoodDTOList(List<RatingDTO> ratingFoodDTOList) {
        this.ratingFoodDTOList = ratingFoodDTOList;
    }
}
