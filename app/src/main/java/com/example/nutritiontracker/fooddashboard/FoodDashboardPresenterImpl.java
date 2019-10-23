package com.example.nutritiontracker.fooddashboard;

import com.example.nutritiontracker.food.Food;
import com.example.nutritiontracker.food.FoodModel;
import com.example.nutritiontracker.user.UserModel;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FoodDashboardPresenterImpl implements FoodDashboardContract.Presenter {
    private FoodDashboardContract.View iView;
    private FoodDashboardContract.Model iModel;
    public FoodDashboardPresenterImpl(FoodDashboardContract.View iView) {
        this.iView = iView;
        iModel = new FoodModel(iView.getContext());
    }

    @Override
    public void setDataToRecyclerView() {
        iView.setDataToRecyclerView(iModel.getFoodListFromDbByDate());
    }

    @Override
    public String getTextMessage(List<Food> foodList) {
        if(foodList.isEmpty()){
            return "Nothing here. Add foods by using the 'ADD' button";
        }
        else {
            return getTotalCaloriesToday();
        }
    }

    @Override
    public String getTotalCaloriesToday() {
        double totalCalories = 0;
        for(Food food : iModel.getFoodListFromDbByDate()){
            totalCalories += food.getNfCalories();
        }
        NumberFormat formatter = new DecimalFormat("#0.00");
        return "Total calories burned today : "+formatter.format(totalCalories)+"";
    }
}
