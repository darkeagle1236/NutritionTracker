package com.example.nutritiontracker.add.addexercise;

import android.content.Context;

import com.example.nutritiontracker.add.AdditionContract;
import com.example.nutritiontracker.exercise.Exercise;
import com.example.nutritiontracker.exercise.ExerciseModel;
import com.example.nutritiontracker.food.Food;
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
        iView.setExerciseDataToRecyclerView(exerciseList);
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

    }

    @Override
    public void onError(int errorCode) {

    }
}
