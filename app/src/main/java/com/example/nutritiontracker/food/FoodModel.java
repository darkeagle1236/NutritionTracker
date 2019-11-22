package com.example.nutritiontracker.food;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nutritiontracker.add.AdditionContract;
import com.example.nutritiontracker.database.DatabaseHelper;
import com.example.nutritiontracker.fooddashboard.FoodDashboardContract;
import com.example.nutritiontracker.network.GetDataService;
import com.example.nutritiontracker.network.RetrofitClientInstance;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodModel implements AdditionContract.Model.FoodModel, FoodDashboardContract.Model,AdditionContract.Model {
    public static final String TABLE_NAME = "FOOD";
    public static final String CREATE_TABLE_FOOD = "CREATE TABLE [FOOD] (\n" +
            "[id] INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "[foodname] NVARCHAR(255)  NULL,\n" +
            "[quantity] NVARCHAR(50)  NULL,\n" +
            "[unit] NVARCHAR(50)  NULL,\n" +
            "[weight] NVARCHAR(50)  NULL,\n" +
            "[calories] NVARCHAR(50)  NULL,\n" +
            "[totalfat] NVARCHAR(50)  NULL,\n" +
            "[saturatedfat] NVARCHAR(50)  NULL,\n" +
            "[choresterol] NVARCHAR(50)  NULL,\n" +
            "[sodium] NVARCHAR(50)  NULL,\n" +
            "[totalcarbonhydrate] NVARCHAR(50)  NULL,\n" +
            "[dietaryfiber] NVARCHAR(50)  NULL,\n" +
            "[sugars] NVARCHAR(50)  NULL,\n" +
            "[protein] NVARCHAR(50)  NULL,\n" +
            "[datetime] DATE  NULL,\n" +
            "[photothumb] NVARCHAR(255)  NULL,\n" +
            "[photohighres] NVARCHAR(255)  NULL\n" +
            ")";
    private SQLiteDatabase sqLiteDatabase;
    private DatabaseHelper databaseHelper;
    public FoodModel(Context context){
        databaseHelper = new DatabaseHelper(context);
        sqLiteDatabase = databaseHelper.getWritableDatabase();
    }
    @Override
    public List<Food> getFoodListFromDb(){
        List<Food> list = new ArrayList<Food>();
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME,null,null,null
                ,null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            Food food = new Food();
            food.setFoodId(cursor.getString(0));
            food.setFoodName(cursor.getString(1));
            food.setServingQty(Integer.valueOf(cursor.getString(2)));
            food.setServingUnit(cursor.getString(3));
            food.setServingWeightGrams(Double.parseDouble(cursor.getString(4)));
            food.setNfCalories(Double.valueOf(cursor.getString(5)));
            food.setNfTotalFat(Double.valueOf(cursor.getString(6)));
            food.setNfSaturatedFat(Double.valueOf(cursor.getString(7)));
            food.setNfCholesterol(Double.valueOf(cursor.getString(8)));
            food.setNfSodium(cursor.getDouble(9));
            food.setNfTotalCarbohydrate(cursor.getDouble(10));
            food.setNfDietaryFiber(cursor.getDouble(11));
            food.setNfSugars(cursor.getDouble(12));
            food.setNfProtein(cursor.getDouble(13));
            food.setDate(cursor.getString(14));
            food.setPhoto(new Photo(cursor.getString(15),cursor.getString(16)));
            list.add(food);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    @Override
    public List<Food> getFoodListFromDbByDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
        String date = simpleDateFormat.format(new Date());
        String whereClause = "datetime = ?";
        String[] whereArgs = {date};
        List<Food> list = new ArrayList<Food>();
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME,null,whereClause,whereArgs
                ,null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            Food food = new Food();
            food.setFoodId(cursor.getString(0));
            food.setFoodName(cursor.getString(1));
            food.setServingQty(Integer.valueOf(cursor.getString(2)));
            food.setServingUnit(cursor.getString(3));
            food.setServingWeightGrams(Double.parseDouble(cursor.getString(4)));
            food.setNfCalories(Double.valueOf(cursor.getString(5)));
            food.setNfTotalFat(Double.valueOf(cursor.getString(6)));
            food.setNfSaturatedFat(Double.valueOf(cursor.getString(7)));
            food.setNfCholesterol(Double.valueOf(cursor.getString(8)));
            food.setNfSodium(cursor.getDouble(9));
            food.setNfTotalCarbohydrate(cursor.getDouble(10));
            food.setNfDietaryFiber(cursor.getDouble(11));
            food.setNfSugars(cursor.getDouble(12));
            food.setNfProtein(cursor.getDouble(13));
            food.setDate(cursor.getString(14));
            food.setPhoto(new Photo(cursor.getString(15),cursor.getString(16)));
            list.add(food);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    @Override
    public void deleteFood(Food food) {

    }

    @Override
    public int insertFood(Food food){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
        String date = simpleDateFormat.format(new Date());
        System.out.println("dateeeeeee"+date);
        ContentValues values = new ContentValues();
        values.put("foodname",food.getFoodName());
        values.put("quantity",food.getServingQty());
        values.put("unit",food.getServingUnit());
        values.put("weight",food.getServingWeightGrams().toString());
        values.put("calories",food.getNfCalories());
        values.put("totalfat",food.getNfTotalFat());
        values.put("saturatedfat",food.getNfSaturatedFat());
        values.put("choresterol",food.getNfCholesterol());
        values.put("sodium",food.getNfSodium());
        values.put("totalcarbonhydrate",food.getNfTotalCarbohydrate());
        values.put("dietaryfiber",food.getNfDietaryFiber());
        values.put("sugars",food.getNfSugars());
        values.put("protein",food.getNfProtein());
        values.put("datetime",date);
        values.put("photothumb",food.getPhoto().getThumb());
        values.put("photohighres",food.getPhoto().getHighres());
        if(sqLiteDatabase.insert(TABLE_NAME,null,values)<0){
            return -1;
        }
        else {
            return 1;
        }
    }

    @Override
    public void getFoodList(final AdditionContract.Model.FoodModel.OnFinishedListener onFinishedListener , String query){
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<FoodListResponse> call = service.getFoodList(query);
        call.enqueue(new Callback<FoodListResponse>() {
            @Override
            public void onResponse(Call<FoodListResponse> call, Response<FoodListResponse> response) {
                if (response.isSuccessful()) {
                    List<Food> foodList = response.body().getFoods();
                    onFinishedListener.onFinished(foodList);
                }
                else if(response.code()==404) {
                    onFinishedListener.onError(response.code());
                }
            }
            @Override
            public void onFailure(Call<FoodListResponse> call, Throwable t) {
                t.printStackTrace();
                onFinishedListener.onFailure(t);
            }
        });
    }
}
