
package com.example.nutritiontracker.exercise;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExerciseResponse {

    @SerializedName("exercises")
    @Expose
    private List<Exercise> exercises = null;

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

}
