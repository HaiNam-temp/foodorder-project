package com.example.beprojec2.DTO;

import java.util.List;

public class CategoryDTO {
    private int categoryid;
    private String categoryname;
    private List<FoodHomeDTO> foodlist;

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public List<FoodHomeDTO> getFoodlist() {
        return foodlist;
    }

    public void setFoodlist(List<FoodHomeDTO> foodlist) {
        this.foodlist = foodlist;
    }
}
