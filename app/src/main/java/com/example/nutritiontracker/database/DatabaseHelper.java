package com.example.nutritiontracker.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.nutritiontracker.exercise.ExerciseModel;
import com.example.nutritiontracker.food.FoodModel;
import com.example.nutritiontracker.user.UserModel;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "NUTRITION";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 4);
    }
    //tao DB
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(UserModel.CREATE_TABLE_USER);
        sqLiteDatabase.execSQL(FoodModel.CREATE_TABLE_FOOD);
        sqLiteDatabase.execSQL(ExerciseModel.CREATE_TABLE_EXERCISE);
    }
    //Cap nhat bang
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ UserModel.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ FoodModel.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ ExerciseModel.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
