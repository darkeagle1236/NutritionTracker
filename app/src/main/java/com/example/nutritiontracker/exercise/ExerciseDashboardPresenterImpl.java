package com.example.nutritiontracker.exercise;

import android.content.Context;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class ExerciseDashboardPresenterImpl implements ExerciseDashboardContract.Presenter {
    private ExerciseDashboardContract.View iView;
    private ExerciseDashboardContract.Model iModel;
    public ExerciseDashboardPresenterImpl(ExerciseDashboardContract.View iView) {
        this.iView = iView;
        iModel = new ExerciseModel(iView.getContext());
    }

    @Override
    public void setDataToRecyclerView() {
        List<Exercise> exerciseList = iModel.getExerciseListFromDbByDate();
        iView.setDataToRecyclerView(exerciseList);
    }

    @Override
    public String getTextMessage(List<Exercise> exerciseList) {
        if(exerciseList.isEmpty()){
            return "Nothing here. Add exercises by using the 'ADD' button";
        }
        else {
            return getTotalCaloriesBurnedToday();
        }
    }

    @Override
    public String getTotalCaloriesBurnedToday() {
        double totalCaloriesBurned = 0;
        for(Exercise exercise : iModel.getExerciseListFromDbByDate()){
            totalCaloriesBurned += exercise.getNfCalories();
        }
        NumberFormat formatter = new DecimalFormat("#0.00");
        return "Total calories burned today : "+formatter.format(totalCaloriesBurned)+"";
    }
}
