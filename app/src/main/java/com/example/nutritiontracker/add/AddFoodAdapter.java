package com.example.nutritiontracker.add;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.nutritiontracker.R;
import com.example.nutritiontracker.food.Food;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AddFoodAdapter extends RecyclerView.Adapter<AddFoodAdapter.CustomViewHolder> {
    private List<Food> foodList;
    private Context context;

    public AddFoodAdapter(Context context, List<Food> foodList){
        this.context = context;
        this.foodList = foodList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView tvFoodName,tvCalories,tvQuantity,tvUnit;
        ImageView ivFood;
        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            tvFoodName = mView.findViewById(R.id.tvFoodName);
            ivFood = mView.findViewById(R.id.ivFood);
            tvCalories = mView.findViewById(R.id.tvCalories);
            tvQuantity = mView.findViewById(R.id.tvQuantity);
            tvUnit = mView.findViewById(R.id.tvUnit);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_food, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.tvFoodName.setText(foodList.get(position).getFoodName());
        Picasso.get()
                .load(foodList.get(position).getPhoto().getThumb())
                .into(holder.ivFood);
        holder.tvUnit.setText("Unit : "+foodList.get(position).getServingUnit());
        holder.tvQuantity.setText("Quantity : "+foodList.get(position).getServingQty().toString()+" | ");
        holder.tvCalories.setText("Calories : "+foodList.get(position).getNfCalories().toString());
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

}