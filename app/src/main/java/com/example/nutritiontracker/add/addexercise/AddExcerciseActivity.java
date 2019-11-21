package com.example.nutritiontracker.add.addexercise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nutritiontracker.R;
import com.example.nutritiontracker.activity.MainActivity;
import com.example.nutritiontracker.add.AdditionContract;
import com.example.nutritiontracker.exercise.Exercise;
import com.example.nutritiontracker.food.Food;

import java.util.ArrayList;
import java.util.List;

public class AddExcerciseActivity extends AppCompatActivity implements AdditionContract.View {
    RecyclerView rclvExercises;
    RecyclerView.LayoutManager mLayoutManager;
    EditText edtFindExercises;
    Button btnFind,btnAdd;
    TextView tvErrorMsg;
    AddExerciseAdapter adapter;
    List<Exercise> exerciseList;
    private ProgressBar pbLoading;
    CardView cdvExerciseList;
    AdditionContract.Presenter.ExercisePresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_excercise);
        presenter = new AddExercisePresenterImpl(this);
        initUI();
    }

    @Override
    public void clearAllItem() {
        exerciseList.clear();
    }

    @Override
    public String getQuery() {
        return edtFindExercises.getText().toString();
    }

    @Override
    public void initUI() {
        cdvExerciseList = findViewById(R.id.cdvExerciseList);
        exerciseList = new ArrayList<>();
        rclvExercises = findViewById(R.id.rclvExercises);
        edtFindExercises = findViewById(R.id.edtFindExercises);
        btnFind = findViewById(R.id.btnFind);
        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.requestDataFromServer();
            }
        });
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.insertExerciseToDb(exerciseList);
            }
        });
        tvErrorMsg = findViewById(R.id.tvErrorMsg);
        pbLoading = findViewById(R.id.pb_loading);
        adapter = new AddExerciseAdapter(this, exerciseList);
        mLayoutManager = new LinearLayoutManager(this);
        rclvExercises.setLayoutManager(mLayoutManager);
        rclvExercises.setItemAnimator(new DefaultItemAnimator());
        rclvExercises.setAdapter(adapter);
    }

    @Override
    public void setFoodDataToRecyclerView(List<Food> foodList) {

    }

    @Override
    public void setExerciseDataToRecyclerView(List<Exercise> mExerciseList) {
        exerciseList.addAll(mExerciseList);
        adapter.notifyDataSetChanged();
        rclvExercises.setAdapter(adapter);
        btnAdd.setVisibility(View.VISIBLE);
        btnAdd.setText("ADD " + mExerciseList.size() + " EXERCISE(S)");
    }

    @Override
    public void showProgress() {
        pbLoading.setVisibility(View.VISIBLE);
        cdvExerciseList.setVisibility(View.INVISIBLE);
        btnAdd.setVisibility(View.INVISIBLE);
        tvErrorMsg.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        pbLoading.setVisibility(View.GONE);
        cdvExerciseList.setVisibility(View.VISIBLE);
    }

    @Override
    public void startMainActivity() {
        Intent intent = new Intent(AddExcerciseActivity.this, MainActivity.class);
        intent.putExtra("des_fragment",R.id.navigation_exercise);
        startActivity(intent);
    }

    @Override
    public void displayErrorMsg(String errMsg) {
        tvErrorMsg.setText(errMsg);
        tvErrorMsg.setVisibility(View.VISIBLE);
    }
}
