package com.example.nutritiontracker.fooddashboard;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.nutritiontracker.R;
import com.example.nutritiontracker.food.Food;
import com.example.nutritiontracker.fooddetail.FoodDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FoodDashboardAdapter extends RecyclerView.Adapter<FoodDashboardAdapter.CustomViewHolder> {
    private List<Food> foodList;
    private Context context;

    public FoodDashboardAdapter(Context context, List<Food> foodList){
        this.context = context;
        this.foodList = foodList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView tvFoodName,tvCalories,tvQuantity,tvUnit;
        ImageView ivFood,ivDelete;
        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            tvFoodName = mView.findViewById(R.id.tvFoodName);
            ivFood = mView.findViewById(R.id.ivFood);
            tvCalories = mView.findViewById(R.id.tvCalories);
            tvQuantity = mView.findViewById(R.id.tvQuantity);
            tvUnit = mView.findViewById(R.id.tvUnit);
            ivDelete = mView.findViewById(R.id.ivDelete);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_food_horizontal, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, int position) {
        holder.tvFoodName.setText(foodList.get(position).getFoodName());
        Picasso.get()
                .load(foodList.get(position).getPhoto().getThumb())
                .into(holder.ivFood);
        holder.tvUnit.setText("Unit : "+foodList.get(position).getServingUnit());
        holder.tvQuantity.setText("Quantity : "+foodList.get(position).getServingQty().toString()+" | ");
        holder.tvCalories.setText("Calories : "+foodList.get(position).getNfCalories().toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FoodDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("foodname",foodList.get(holder.getAdapterPosition()).getFoodName());
                bundle.putString("calories",foodList.get(holder.getAdapterPosition()).getNfCalories().toString());
                bundle.putString("quantity",foodList.get(holder.getAdapterPosition()).getServingQty().toString());
                bundle.putString("unit", foodList.get(holder.getAdapterPosition()).getServingUnit());
                bundle.putString("weight",foodList.get(holder.getAdapterPosition()).getServingWeightGrams().toString());
                bundle.putString("choresterol",foodList.get(holder.getAdapterPosition()).getNfCholesterol().toString());
                bundle.putString("dietaryfiber",foodList.get(holder.getAdapterPosition()).getNfDietaryFiber().toString());
                bundle.putString("totalfat",foodList.get(holder.getAdapterPosition()).getNfTotalFat().toString());
                bundle.putString("saturatedfat",foodList.get(holder.getAdapterPosition()).getNfSaturatedFat().toString());
                bundle.putString("sodium",foodList.get(holder.getAdapterPosition()).getNfSodium().toString());
                bundle.putString("sugars",foodList.get(holder.getAdapterPosition()).getNfSugars().toString());
                bundle.putString("protein",foodList.get(holder.getAdapterPosition()).getNfProtein().toString());
                bundle.putString("totalcarbonhydrate",foodList.get(holder.getAdapterPosition()).getNfTotalCarbohydrate().toString());
                bundle.putString("imageurl",foodList.get(holder.getAdapterPosition()).getPhoto().getHighres());
                intent.putExtra("bundle",bundle);
                context.startActivity(intent);
            }
        });
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                foodList.remove(holder.getAdapterPosition());
                                notifyDataSetChanged();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

}
