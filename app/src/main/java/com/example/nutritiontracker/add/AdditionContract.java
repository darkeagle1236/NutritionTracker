package com.example.nutritiontracker.add;

import com.example.nutritiontracker.food.Food;

import java.util.List;

public interface AdditionContract {
    interface Model{
        interface OnFinishedListener {
            void onFinished(List<Food> foodList);

            void onFailure(Throwable t);
        }
        int insertFood(Food food);
        void getFoodList(final OnFinishedListener onFinishedListener ,String query);
    }
    interface View{
        void clearAllItem();
        String getQuery();
        void initUI();
        void setDataToRecyclerView(List<Food> mfoodList);
        void showProgress();
        void hideProgress();
        void startMainActivity();
    }
    interface Presenter{
        void requestDataFromServer();
        void onFinished(List<Food> foodList);
        void insertFoodToDb(List<Food> foodList);
    }
}
