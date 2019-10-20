package com.example.nutritiontracker.user;

import java.util.List;

public interface UserContract {
    interface Model{
        int insertUser(User user);
        List<User> getAllUser();
        int updateUser(String id,String gender,String weight,String height,String age);
    }
    interface View{
        User getUserInfo();
        void startCreateUserActivity();
        void startMainActivity();
    }
    interface Presenter{
        boolean isUserExist();
        int insertUser();
        void onBtnSubmitClicked();
        void onSplashFinished();
    }
}
