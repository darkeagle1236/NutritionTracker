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
import com.example.nutritiontracker.add.addexercise.AddExcerciseActivity;
import com.example.nutritiontracker.add.addfood.AddFoodActivity;

public class AddFragment extends Fragment {
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
                Intent intent = new Intent(view.getContext(), AddFoodActivity.class);
                startActivity(intent);
            }
        });
        btnExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddExcerciseActivity.class);
                startActivity(intent);
            }
        });
    }
}