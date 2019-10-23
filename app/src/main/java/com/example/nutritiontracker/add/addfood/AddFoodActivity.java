package com.example.nutritiontracker.add.addfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.nutritiontracker.R;
import com.example.nutritiontracker.activity.MainActivity;
import com.example.nutritiontracker.add.AdditionContract;
import com.example.nutritiontracker.exercise.Exercise;
import com.example.nutritiontracker.food.Food;

import java.util.ArrayList;
import java.util.List;

public class AddFoodActivity extends AppCompatActivity implements AdditionContract.View {
    Button btnFind, btnAdd;
    EditText edtFindFood;
    private ProgressBar pbLoading;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView rclvFood;
    List<Food> foodList;
    AddFoodPresenterImpl presenter;
    AddFoodAdapter adapter;
    TextView tvErrMsg;
    CardView cdvFoodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        presenter = new AddFoodPresenterImpl(this);
        initUI();
    }

    @Override
    public void clearAllItem() {
        foodList.clear();
    }

    @Override
    public String getQuery() {
        return edtFindFood.getText().toString();
    }

    @Override
    public void initUI() {
        cdvFoodList = findViewById(R.id.cdvFoodList);
        tvErrMsg = findViewById(R.id.tvErrorMsg);
        pbLoading = findViewById(R.id.pb_loading);
        btnAdd = findViewById(R.id.btnAdd);
        edtFindFood = findViewById(R.id.edtFindFood);
        btnFind = findViewById(R.id.btnFind);
        rclvFood = findViewById(R.id.rclvFood);
        foodList = new ArrayList<>();
        adapter = new AddFoodAdapter(this, foodList);
        mLayoutManager = new LinearLayoutManager(this);
        rclvFood.setLayoutManager(mLayoutManager);
        rclvFood.setItemAnimator(new DefaultItemAnimator());
        rclvFood.setAdapter(adapter);
        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.requestDataFromServer();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.insertFoodToDb(foodList);
            }
        });
    }

    @Override
    public void setFoodDataToRecyclerView(List<Food> mfoodList) {
        foodList.addAll(mfoodList);
        adapter.notifyDataSetChanged();
        rclvFood.setAdapter(adapter);
        btnAdd.setVisibility(View.VISIBLE);
        btnAdd.setText("ADD " + mfoodList.size() + " FOOD(S)");
        cdvFoodList.setVisibility(View.VISIBLE);
    }

    @Override
    public void setExerciseDataToRecyclerView(List<Exercise> mExerciseList) {

    }

    @Override
    public void showProgress() {
        pbLoading.setVisibility(View.VISIBLE);
        cdvFoodList.setVisibility(View.INVISIBLE);
        btnAdd.setVisibility(View.INVISIBLE);
        tvErrMsg.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        pbLoading.setVisibility(View.GONE);
    }

    @Override
    public void startMainActivity() {
        Intent intent = new Intent(AddFoodActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void displayErrorMsg(String errMsg) {
        tvErrMsg.setText(errMsg);
        tvErrMsg.setVisibility(View.VISIBLE);
    }
}
