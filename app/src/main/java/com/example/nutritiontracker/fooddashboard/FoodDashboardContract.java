package com.example.nutritiontracker.fooddashboard;

import android.content.Context;

import com.example.nutritiontracker.food.Food;

import java.util.List;

public interface FoodDashboardContract {
    interface Model{
        List<Food> getFoodListFromDb();
        List<Food> getFoodListFromDbByDate();
    }
    interface View{
        Context getContext();
        void initUI(android.view.View view);
        void setDataToRecyclerView(List<Food> mfoodList);
    }
    interface Presenter{
        void setDataToRecyclerView();
        String getTextMessage(List<Food> foodList);
        String getTotalCaloriesToday();
    }
}
