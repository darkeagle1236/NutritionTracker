package com.example.nutritiontracker.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.nutritiontracker.R;
import com.example.nutritiontracker.activity.MainActivity;

import java.util.ArrayList;

public class CreateUserActivity extends AppCompatActivity implements UserContract.View {
    EditText edtName,edtWeight,edtHeight,edtAge;
    Spinner spnGender;
    Button btnSubmit;
    UserContract.Presenter iPresenter;
    Intent mainIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        initUI();
        initSpinner();
        iPresenter = new UserPresenterImpl(this);
    }
    public void initUI(){
        edtName = findViewById(R.id.edtName);
        edtAge = findViewById(R.id.edtAge);
        edtHeight = findViewById(R.id.edtHeight);
        edtWeight = findViewById(R.id.edtWeight);
        spnGender = findViewById(R.id.spnGender);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iPresenter.insertUser();
            }
        });
    }
    public void initSpinner() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("male");
        arrayList.add("female");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnGender.setAdapter(arrayAdapter);
    }

    @Override
    public User getUserInfo() {
        return new User("1",spnGender.getSelectedItem().toString(),edtWeight.getText().toString(),edtHeight.getText().toString(),edtAge.getText().toString());
    }

    @Override
    public void startCreateUserActivity() {
    }

    @Override
    public void startMainActivity() {
        mainIntent = new Intent(CreateUserActivity.this, MainActivity.class);
        CreateUserActivity.this.startActivity(mainIntent);
        CreateUserActivity.this.finish();
    }
}
