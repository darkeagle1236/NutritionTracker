package com.example.nutritiontracker.dashboard;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nutritiontracker.database.DatabaseHelper;
import com.example.nutritiontracker.exercise.Exercise;
import com.example.nutritiontracker.exercise.ExerciseModel;
import com.example.nutritiontracker.food.FoodModel;
import com.example.nutritiontracker.user.UserModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DashboardModel implements DashboardContract.Model {
    private SQLiteDatabase sqLiteDatabase;
    private DatabaseHelper databaseHelper;
    public DashboardModel(Context context){
        databaseHelper = new DatabaseHelper(context);
        sqLiteDatabase = databaseHelper.getWritableDatabase();
    }
    @Override
    public String getUsername() {
        String username = "";
        Cursor cursor = sqLiteDatabase.query(UserModel.TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            username =cursor.getString(5);
            cursor.moveToNext();
        }
        return username;
    }
    @Override
    public double getTodayAddedCalories() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
        String date = simpleDateFormat.format(new Date());
        String whereClause = "datetime = ?";
        String[] whereArgs = {date};
        double totalAddedCalories = 0;
        Cursor cursor = sqLiteDatabase.query(FoodModel.TABLE_NAME, null, whereClause,whereArgs, null, null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            totalAddedCalories += Double.parseDouble(cursor.getString(5));
            cursor.moveToNext();
        }
        return totalAddedCalories;
    }

    @Override
    public double getThisMonthAddedCalories() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");
        String currentMonth = simpleDateFormat.format(new Date());
        double totalAddedCalories = 0;
        Cursor cursor = sqLiteDatabase.query(FoodModel.TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();

        while (cursor.isAfterLast()==false){
            try {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");;
                Date date = dateFormat.parse(cursor.getString(14));
                if ((date.getMonth()+1)==Integer.parseInt(currentMonth)){
                    totalAddedCalories += Double.parseDouble(cursor.getString(5));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            cursor.moveToNext();
        }
        return totalAddedCalories;
    }

    @Override
    public double getTodayBurnedCalories() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
        String date = simpleDateFormat.format(new Date());
        String whereClause = "datetime = ?";
        String[] whereArgs = {date};
        double totalBurnedCalories = 0;
        Cursor cursor = sqLiteDatabase.query(ExerciseModel.TABLE_NAME, null, whereClause,whereArgs, null, null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            totalBurnedCalories += Double.parseDouble(cursor.getString(3));
            cursor.moveToNext();
        }
        return totalBurnedCalories;
    }

    @Override
    public double getThisMonthBurnedCalories() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");
        String currentMonth = simpleDateFormat.format(new Date());
        double totalAddedCalories = 0;
        Cursor cursor = sqLiteDatabase.query(ExerciseModel.TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();

        while (cursor.isAfterLast()==false){
            try {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");;
                Date date = dateFormat.parse(cursor.getString(5));
                System.out.println("cursor 5"+cursor.getString(5));
                System.out.println("aaaaaa"+(date.getMonth()+1));
                if ((date.getMonth()+1)==Integer.parseInt(currentMonth)){
                    totalAddedCalories += Double.parseDouble(cursor.getString(3));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            cursor.moveToNext();
        }
        return totalAddedCalories;
    }

    @Override
    public double getHeight() {
        double height = 0;
        Cursor cursor = sqLiteDatabase.query(UserModel.TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            height += Double.parseDouble(cursor.getString(3));
            cursor.moveToNext();
        }
        return height;
    }
    @Override
    public double getWeight() {
        double weight = 0;
        Cursor cursor = sqLiteDatabase.query(UserModel.TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            weight += Double.parseDouble(cursor.getString(2));
            cursor.moveToNext();
        }
        return weight;
    }
}
