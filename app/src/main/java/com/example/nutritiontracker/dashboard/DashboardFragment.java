package com.example.nutritiontracker.dashboard;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.nutritiontracker.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;


public class DashboardFragment extends Fragment implements DashboardContract.View {
    Context context;
    TextView tvBMIScore,tvBMIDes,tvTodayAddedCalories,tvThisMonthAddedCalories,tvTodayBurnedCalories,tvThisMonthBurnedCalories,tvUsername;
    DashboardContract.Presenter presenter;
    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        context = root.getContext();
        presenter = new DashboardPresenterImpl(this);
        initUI(root);
        return root;
    }


    @Override
    public void setUsername() {
        tvUsername.setText("Welcome, "+presenter.initUsername());
    }

    @Override
    public void setTodayAddedCalories() {
        tvTodayAddedCalories.setText(presenter.initAddedCaloriesToday()+" calories");
    }

    @Override
    public void setThisMonthAddedCalories() {
        tvThisMonthAddedCalories.setText("This month : "+presenter.initAddedCaloriesThisMonth()+" calories");
    }
    @Override
    public void initUI(View view) {
        tvBMIScore = view.findViewById(R.id.tvBMIScore);
        tvBMIDes = view.findViewById(R.id.tvBMIDes);
        tvThisMonthAddedCalories = view.findViewById(R.id.tvCaloriesAddedThisMonth);
        tvThisMonthBurnedCalories = view.findViewById(R.id.tvCaloriesBurnedThisMonth);
        tvTodayAddedCalories = view.findViewById(R.id.tvCaloriesAddedToday);
        tvTodayBurnedCalories = view.findViewById(R.id.tvCaloriesBurnedToday);
        tvUsername = view.findViewById(R.id.tvGoodMorning);
        setBMIScore();
        setBMIInfo();
        setTodayAddedCalories();
        setThisMonthAddedCalories();
        setUsername();
    }
    @Override
    public Context getContext(){
        return context;
    }

    @Override
    public void setBMIScore() {
        NumberFormat formatter = new DecimalFormat("#0.00");
        tvBMIScore.setText(formatter.format(presenter.calculateBMIScore())+"");
    }

    @Override
    public void setBMIInfo() {
        tvBMIDes.setText(presenter.initBMIInfo());
    }
}
