package com.example.nutritiontracker.fooddashboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nutritiontracker.R;
import com.example.nutritiontracker.activity.MainActivity;
import com.example.nutritiontracker.add.addfood.AddFoodActivity;
import com.example.nutritiontracker.food.Food;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class FoodDashboardFragment extends Fragment implements FoodDashboardContract.View {
    Context context;
    RecyclerView rclvFood;
    FoodDashboardAdapter adapter;
    RecyclerView.LayoutManager mLayoutManager;
    List<Food> foodList;
    TextView tvCalories;
    FloatingActionButton fab;
    FoodDashboardContract.Presenter presenter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_food, container, false);
        context = getActivity();
        presenter = new FoodDashboardPresenterImpl(this);
        initUI(root);
        presenter.setDataToRecyclerView();
        tvCalories.setText(presenter.getTextMessage(foodList));
        return root;
    }

    @Override
    public void initUI(android.view.View view) {
        foodList = new ArrayList<>();
        rclvFood = view.findViewById(R.id.rclvFood);
        adapter = new FoodDashboardAdapter(view.getContext(), foodList);
        mLayoutManager = new LinearLayoutManager(view.getContext(),RecyclerView.HORIZONTAL,false);
        rclvFood.setLayoutManager(mLayoutManager);
        rclvFood.setItemAnimator(new DefaultItemAnimator());
        rclvFood.setAdapter(adapter);
        tvCalories = view.findViewById(R.id.tvCalories);
        fab = (FloatingActionButton)  view.findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
                Intent intent = new Intent(context, AddFoodActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void setDataToRecyclerView(List<Food> mfoodList) {
        foodList.addAll(mfoodList);
        adapter.notifyDataSetChanged();
        rclvFood.setAdapter(adapter);
    }
    @Override
    public Context getContext(){
        return context;
    }
}