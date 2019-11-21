package com.example.nutritiontracker.dashboard;

import android.content.Context;

public interface DashboardContract {
    interface Model{
         String getUsername();
         double getTodayAddedCalories();
         double getThisMonthAddedCalories();
         double getHeight();
         double getWeight();
    }
    interface View{
         void setUsername();
         void setTodayAddedCalories();
         void setThisMonthAddedCalories();
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
         String initBurnedCalories();
         String initBMIInfo();
    }
}
