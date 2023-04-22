package com.example.facedetectorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatDrawableManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignInActivity extends AppCompatActivity {

    Button signUp,signIn;
    TextView usernameText,passwordText;
    EditText username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        signUp = findViewById(R.id.to_registration);
        signIn = findViewById(R.id.to_main);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        usernameText = findViewById(R.id.username_text);
        passwordText = findViewById(R.id.password_text);
        final boolean[] flag1 = {false};
        final boolean[] flag2 = {false};
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toRegister = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(toRegister);
            }
        });
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(username.getText().toString().isEmpty()){
                    username.setBackground(AppCompatResources.getDrawable(getApplicationContext(),R.drawable.error_edit_text_border));
                }
                else{
                    username.setBackground(AppCompatResources.getDrawable(getApplicationContext(),R.drawable.ok_edit_text_border));
                    flag1[0] = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(password.getText().toString().isEmpty()){
                    password.setBackground(AppCompatResources.getDrawable(getApplicationContext(),R.drawable.error_edit_text_border));
                }
                else{
                    password.setBackground(AppCompatResources.getDrawable(getApplicationContext(),R.drawable.ok_edit_text_border));
                    flag2[0] = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag1[0]&&flag2[0]){
                    Intent toSearch = new Intent(getApplicationContext(),SearchPageActivity.class);
                    startActivity(toSearch);
                }
            }
        });
    }
}