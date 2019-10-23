package com.example.nutritiontracker.add.addfood;

import android.content.Context;

import com.example.nutritiontracker.add.AdditionContract;
import com.example.nutritiontracker.food.Food;
import com.example.nutritiontracker.food.FoodModel;

import java.util.List;

public class AddFoodPresenterImpl implements AdditionContract.Presenter,AdditionContract.Model.FoodModel.OnFinishedListener,AdditionContract.Presenter.FoodPresenter {
    AdditionContract.View iView;
    AdditionContract.Model.FoodModel iFoodModel;

    public AddFoodPresenterImpl(AdditionContract.View iView) {
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
        iView.setFoodDataToRecyclerView(foodList);
        iView.hideProgress();
    }

    @Override
    public void insertFoodToDb(List<Food> foodList) {
        for(Food food : foodList){
            iFoodModel.insertFood(food);
        }
        iView.startMainActivity();
    }

    @Override
    public void onError(int errorCode) {
        if(errorCode==404){
            iView.displayErrorMsg("Not found your food. Please try again !");
        }
        else if(errorCode == 400){
            iView.displayErrorMsg("Bad request. Check your internet connection");
        }
        else {
            iView.displayErrorMsg("Unknown error. Restarting the app may help");
        }
        iView.hideProgress();
    }

    @Override
    public void onFailure(Throwable t) {
        iView.displayErrorMsg(t.getMessage());
        iView.hideProgress();
    }
}
