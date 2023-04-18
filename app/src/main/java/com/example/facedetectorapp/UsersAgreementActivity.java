package com.example.facedetectorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class UsersAgreementActivity extends AppCompatActivity {

    Boolean flag = false;
    Button nextBut;
    CheckBox agreement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_agreement);
        agreement = findViewById(R.id.agreement);
        nextBut = findViewById(R.id.to_main_activity);
        nextBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agreement.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        flag = isChecked;
                    }
                });
                if(flag) {
                    Intent toMain = new Intent(getApplicationContext(), RegisterActivity.class);
                    startActivity(toMain);
                }
            }
        });
    }
}