package com.example.nutritiontracker.exercise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.nutritiontracker.R;

import java.util.List;

public class ExerciseDashboardAdapter extends RecyclerView.Adapter<ExerciseDashboardAdapter.CustomViewHolder> {
    private List<Exercise> exerciseList;
    private Context context;

    public ExerciseDashboardAdapter(Context context, List<Exercise> exerciseList){
        this.context = context;
        this.exerciseList = exerciseList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView tvExerciseName,tvUserInput,tvCalories,tvDuration;
        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            tvExerciseName = mView.findViewById(R.id.tvExerciseName);
            tvUserInput = mView.findViewById(R.id.tvUserInput);
            tvCalories = mView.findViewById(R.id.tvCalories);
            tvDuration = mView.findViewById(R.id.tvDuration);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_exercise, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.tvExerciseName.setText(exerciseList.get(position).getName());
        holder.tvCalories.setText("Calories burned : "+exerciseList.get(position).getNfCalories());
        holder.tvDuration.setText("Duration : " + exerciseList.get(position).getDurationMin()+" (mins)");
        holder.tvUserInput.setText("("+exerciseList.get(position).getUserInput() +")");
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }

}