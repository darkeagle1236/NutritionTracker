package com.example.nutritiontracker.dashboard;

import android.content.Context;

public interface DashboardContract {
    interface Model{
         String getUsername();
         double getTodayAddedCalories();
         double getThisMonthAddedCalories();
         double getTodayBurnedCalories();
         double getThisMonthBurnedCalories();
         double getHeight();
         double getWeight();
    }
    interface View{
         void setUsername();
         void setTodayAddedCalories();
         void setThisMonthAddedCalories();
         void setTodayBurnedCalories();
         void setThisMonthBurnedCalories();
         void initUI(android.view.View view);
         Context getContext();
         void setBMIScore();
         void setBMIInfo();
    }
    interface Presenter{
         double calculateBMIScore();
         String initUsername();
         String initAddedCaloriesToday();
         String initAddedCaloriesThisMonth();
         String initBurnedCaloriesToday();
         String initBurnedCaloriesThisMonth();
         String initBurnedCalories();
         String initBMIInfo();
    }
}
