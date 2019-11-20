package com.example.nutritiontracker.add.addexercise;

import android.content.Context;

import com.example.nutritiontracker.add.AdditionContract;
import com.example.nutritiontracker.exercise.Exercise;
import com.example.nutritiontracker.exercise.ExerciseModel;
import com.example.nutritiontracker.user.UserContract;
import com.example.nutritiontracker.user.UserModel;

import java.util.List;

public class AddExercisePresenterImpl implements AdditionContract.Presenter.ExercisePresenter, AdditionContract.Model.ExerciseModel.OnFinishedListener {
    AdditionContract.View iView;
    AdditionContract.Model.ExerciseModel iExerciseModel;
    UserContract.Model iUserModel;
    public AddExercisePresenterImpl(AdditionContract.View iView) {
        this.iView = iView;
        iExerciseModel = new ExerciseModel((Context) iView);
        iUserModel = new UserModel(iView);
    }


    @Override
    public void onFinished(List<Exercise> exerciseList) {
        iView.clearAllItem();
        iView.setExerciseDataToRecyclerView(exerciseList);
        iView.hideProgress();
    }

    @Override
    public void insertExerciseToDb(List<Exercise> exerciseList) {
        for(Exercise exercise : exerciseList){
            iExerciseModel.insertExercise(exercise);
        }
        iView.startMainActivity();
    }

    @Override
    public void requestDataFromServer() {
        iView.showProgress();
        iExerciseModel.getExerciseList(this,iView.getQuery(),iUserModel.getAllUser().get(0));
    }

    @Override
    public void onFailure(Throwable t) {
        iView.displayErrorMsg(t.getMessage());
        iView.hideProgress();
    }

    @Override
    public void onError(int errorCode) {
        if(errorCode==404){
            iView.displayErrorMsg("Not found your exercise. Please try again !");
        }
        else if(errorCode == 400){
            iView.displayErrorMsg("Bad request. Check your internet connection");
        }
        else {
            iView.displayErrorMsg("Unknown error. Restarting the app may helps");
        }
        iView.hideProgress();
    }
}
