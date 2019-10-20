package com.example.nutritiontracker.add;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.nutritiontracker.R;
import com.example.nutritiontracker.activity.MainActivity;
import com.example.nutritiontracker.food.Food;

import java.util.List;

public class AddExcerciseActivity extends AppCompatActivity implements AdditionContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_excercise);
    }

    @Override
    public void clearAllItem() {

    }

    @Override
    public String getQuery() {
        return null;
    }

    @Override
    public void initUI() {

    }

    @Override
    public void setDataToRecyclerView(List<Food> foodList) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void startMainActivity() {
        Intent intent = new Intent(AddExcerciseActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
