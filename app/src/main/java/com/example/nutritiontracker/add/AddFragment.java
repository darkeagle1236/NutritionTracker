package com.example.nutritiontracker.add;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nutritiontracker.R;
import com.example.nutritiontracker.food.Food;

import java.util.List;

public class AddFragment extends Fragment implements AdditionContract.View {
    Button btnFood,btnExercise;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_add, container, false);
        initUI(view);
        return view;
    }

    private void initUI(View view) {
        btnFood = view.findViewById(R.id.btnFood);
        btnExercise = view.findViewById(R.id.btnExercise);
        btnFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),AddFoodActivity.class);
                startActivity(intent);
            }
        });
        btnExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),AddExcerciseActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initRecyclerView(){

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

    }
}