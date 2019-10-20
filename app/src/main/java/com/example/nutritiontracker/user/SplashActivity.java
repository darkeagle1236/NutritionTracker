package com.example.nutritiontracker.user;



import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nutritiontracker.activity.MainActivity;

/**
 * Created by ssaurel on 02/12/2016.
 */
public class SplashActivity extends AppCompatActivity implements UserContract.View {
    private final int SPLASH_DISPLAY_LENGTH = 3000;
    private UserContract.Presenter presenter;
    Intent mainIntent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new UserPresenterImpl(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.onSplashFinished();
            }
        },SPLASH_DISPLAY_LENGTH);
    }

    @Override
    public User getUserInfo() {
        return null;
    }

    @Override
    public void startCreateUserActivity() {
        mainIntent = new Intent(SplashActivity.this,CreateUserActivity.class);
        SplashActivity.this.startActivity(mainIntent);
        SplashActivity.this.finish();
    }

    @Override
    public void startMainActivity() {
        mainIntent = new Intent(SplashActivity.this, MainActivity.class);
        SplashActivity.this.startActivity(mainIntent);
        SplashActivity.this.finish();
    }
}
