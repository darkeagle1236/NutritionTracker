package com.example.nutritiontracker.add;

import android.content.Context;

import com.example.nutritiontracker.food.Food;
import com.example.nutritiontracker.food.FoodModel;

import java.util.List;

public class AddPresenterImpl implements AdditionContract.Presenter,AdditionContract.Model.OnFinishedListener {
    AdditionContract.View iView;
    AdditionContract.Model iFoodModel;

    public AddPresenterImpl(AdditionContract.View iView) {
        this.iView =  iView;
        iFoodModel = new FoodModel((Context) iView);
    }


    @Override
    public void requestDataFromServer() {
        iView.showProgress();
        iFoodModel.getFoodList(this,iView.getQuery());
    }

    @Override
    public void onFinished(List<Food> foodList) {
        iView.clearAllItem();
        iView.setDataToRecyclerView(foodList);
        iView.hideProgress();
    }

    @Override
    public void insertFoodToDb(List<Food> foodList) {
        for(Food food : foodList){
            iFoodModel.insertFood(food);
        }
    }

    @Override
    public void onFailure(Throwable t) {

    }
}
