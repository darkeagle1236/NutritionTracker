package com.example.nutritiontracker.exercise;

import android.content.Context;

import com.example.nutritiontracker.food.Food;

import java.util.List;

public interface ExerciseDashboardContract {
    interface Model{
        List<Exercise> getExerciseListFromDb();
        List<Exercise> getExerciseListFromDbByDate();
    }
    interface View{
        Context getContext();
        void initUI(android.view.View view);
        void setDataToRecyclerView(List<Exercise> mExerciseList);
    }
    interface Presenter{
        void setDataToRecyclerView();
        String getTextMessage(List<Exercise> exerciseList);
        String getTotalCaloriesBurnedToday();
    }
}
