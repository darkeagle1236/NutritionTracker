package com.example.nutritiontracker.user;

import android.content.Context;
import android.widget.Toast;

public class UserPresenterImpl implements UserContract.Presenter {
    private UserContract.Model iModel;
    private UserContract.View iView;
    public UserPresenterImpl(UserContract.View iView) {
        this.iView = iView;
        iModel = new UserModel(iView);
    }

    @Override
    public boolean isUserExist() {
        if(iModel.getAllUser().size()==0){
            return false;
        }
        return true;
    }

    @Override
    public int insertUser() {
        if(iModel.insertUser(iView.getUserInfo())>=1){
            iView.startMainActivity();
            return 1;
        }
        else {
            Toast.makeText((Context) iView,"Thêm thất bại",Toast.LENGTH_LONG);
            return 0;
        }
    }

    @Override
    public void onBtnSubmitClicked() {
        if(insertUser()>=1){
            iView.startMainActivity();
        }
    }

    @Override
    public void onSplashFinished() {
        if(isUserExist()){
            iView.startMainActivity();
        }
        else {
            iView.startCreateUserActivity();
        }
    }
}
