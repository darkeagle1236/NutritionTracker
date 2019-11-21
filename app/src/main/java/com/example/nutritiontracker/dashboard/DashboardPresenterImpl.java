package com.example.nutritiontracker.dashboard;

import com.example.nutritiontracker.food.FoodModel;
import com.example.nutritiontracker.fooddashboard.FoodDashboardContract;

public class DashboardPresenterImpl implements DashboardContract.Presenter {
    private DashboardContract.View iView;
    private DashboardContract.Model iModel;
    public DashboardPresenterImpl(DashboardContract.View iView) {
        this.iView = iView;
        iModel = new DashboardModel(iView.getContext());
    }

    @Override
    public double calculateBMIScore() {

        return iModel.getWeight()*10000/(Math.pow(iModel.getHeight(),2));
    }

    @Override
    public String initUsername() {
        return iModel.getUsername();
    }

    @Override
    public String initAddedCaloriesToday() {
        return iModel.getTodayAddedCalories()+"";
    }

    @Override
    public String initBurnedCalories() {
        return iModel.getTodayAddedCalories()+"";
    }

    @Override
    public String initBMIInfo() {
        if(calculateBMIScore()<18.5){
            return "<18.5 : underweight";
        }
        else if(calculateBMIScore()>18.5&&calculateBMIScore()<24.9){
            return "18.5 - 24.9 : normal weight";
        }
        else {
            return ">24.9 : fat";
        }
    }

    @Override
    public String initAddedCaloriesThisMonth() {
        return iModel.getThisMonthAddedCalories()+"";
    }
}
