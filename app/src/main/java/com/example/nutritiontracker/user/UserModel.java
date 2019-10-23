package com.example.nutritiontracker.user;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

import com.example.nutritiontracker.add.AdditionContract;
import com.example.nutritiontracker.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class UserModel implements UserContract.Model {
    public static final String TABLE_NAME = "USER";
    public static final String CREATE_TABLE_USER = "CREATE TABLE [USER] (\n" +
            "[id] INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "[gender] NVARCHAR(50)  NULL,\n" +
            "[weight] NVARCHAR(50)  NULL,\n" +
            "[height] NVARCHAR(50)  NULL,\n" +
            "[age] INTEGER  NULL\n" +
            ")";
    private SQLiteDatabase sqLiteDatabase;
    private DatabaseHelper databaseHelper;
    public UserModel(UserContract.View view){
        databaseHelper = new DatabaseHelper((Context) view);
        sqLiteDatabase = databaseHelper.getWritableDatabase();
    }
    public UserModel(AdditionContract.View view){
        databaseHelper = new DatabaseHelper((Context) view);
        sqLiteDatabase = databaseHelper.getWritableDatabase();
    }
    @Override
    public int insertUser(User user){
        ContentValues values = new ContentValues();
        values.put("id",user.getId());

        values.put("gender",user.getGender());

        values.put("weight",user.getWeight());

        values.put("height",user.getHeight());

        values.put("age",user.getAge());
        if(sqLiteDatabase.insert(TABLE_NAME,null,values)<0){
            return -1;
        }
        else {
            return 1;
        }
    }
    @Override
    public List<User> getAllUser(){
        List<User> list = new ArrayList<User>();
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME,null,null,null
                ,null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            User user = new User();
            user.setId(cursor.getString(0));
            user.setGender(cursor.getString(1));
            user.setWeight(cursor.getString(2));
            user.setHeight(cursor.getString(3));
            user.setAge(cursor.getString(4));
            list.add(user);
            cursor.moveToNext();
        }
        return list;
    }
    @Override
    public int updateUser(String id,String gender,String weight,String height,String age){
        ContentValues values = new ContentValues();
        values.put("gender",gender);
        values.put("weight",weight);
        values.put("height",height);
        values.put("age",age);
        return sqLiteDatabase.update(TABLE_NAME,values,"id=?",new String[]{id});
    }
}
