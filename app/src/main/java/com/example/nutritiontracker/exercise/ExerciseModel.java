package com.example.nutritiontracker.exercise;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nutritiontracker.add.AdditionContract;
import com.example.nutritiontracker.database.DatabaseHelper;
import com.example.nutritiontracker.network.GetDataService;
import com.example.nutritiontracker.network.RetrofitClientInstance;
import com.example.nutritiontracker.user.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExerciseModel implements AdditionContract.Model.ExerciseModel, ExerciseDashboardContract.Model {
    public static final String TABLE_NAME = "EXERCISE";
    public static final String CREATE_TABLE_EXERCISE = "CREATE TABLE [EXERCISE] (\n" +
            "[id] INTEGER  PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
            "[userinput] NVARCHAR(50)  NULL,\n" +
            "[duration] NVARCHAR(50)  NULL,\n" +
            "[calories] NVARCHAR(50)  NULL,\n" +
            "[name] NVARCHAR(50)  NULL,\n" +
            "[datetime] DATE  NULL\n" +
            ")";
    private SQLiteDatabase sqLiteDatabase;
    private DatabaseHelper databaseHelper;
    public ExerciseModel(Context context){
        databaseHelper = new DatabaseHelper(context);
        sqLiteDatabase = databaseHelper.getWritableDatabase();
    }
    @Override
    public int insertExercise(Exercise exercise) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
        String date = simpleDateFormat.format(new Date());
        ContentValues values = new ContentValues();
        values.put("userinput",exercise.getUserInput());
        values.put("duration",exercise.getDurationMin());
        values.put("calories",exercise.getNfCalories());
        values.put("name",exercise.getName());
        values.put("datetime",date);
        if(sqLiteDatabase.insert(TABLE_NAME,null,values)<0){
            return -1;
        }
        else {
            return 1;
        }
    }
    @Override
    public void getExerciseList(final AdditionContract.Model.ExerciseModel.OnFinishedListener onFinishedListener, String query, User user) {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ExerciseResponse> call = service.getExerciseList(query,user.getGender(),Double.parseDouble(user.getWeight()),Double.parseDouble(user.getHeight()),Integer.parseInt(user.getAge()));
        call.enqueue(new Callback<ExerciseResponse>() {
            @Override
            public void onResponse(Call<ExerciseResponse> call, Response<ExerciseResponse> response) {
                if (response.isSuccessful()) {
                    List<Exercise> exerciseList = response.body().getExercises();
                    onFinishedListener.onFinished(exerciseList);
                }
                else if(response.code()==404) {
                    onFinishedListener.onError(response.code());
                }
            }
            @Override
            public void onFailure(Call<ExerciseResponse> call, Throwable t) {
                t.printStackTrace();
                onFinishedListener.onFailure(t);
            }
        });
    }

    @Override
    public List<Exercise> getExerciseListFromDb() {
        return null;
    }

    @Override
    public List<Exercise> getExerciseListFromDbByDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
        String date = simpleDateFormat.format(new Date());
        String whereClause = "datetime = ?";
        String[] whereArgs = {date};
        List<Exercise> list = new ArrayList<Exercise>();
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME,null,whereClause,whereArgs
                ,null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            Exercise exercise = new Exercise();
            exercise.setId(cursor.getString(0));
            exercise.setUserInput(cursor.getString(1));
            exercise.setDurationMin(Double.parseDouble(cursor.getString(2)));
            exercise.setNfCalories(Double.parseDouble(cursor.getString(3)));
            exercise.setName(cursor.getString(4));
            list.add(exercise);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
}
