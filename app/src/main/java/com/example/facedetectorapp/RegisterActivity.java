package com.example.facedetectorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        TextView next = findViewById(R.id.to_verification_stage);
        TextView errorPhoneNumber = findViewById(R.id.phone_number_warning);
        EditText phone = findViewById(R.id.phone_number);
        final boolean[] flag2 = {false};

        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(phone.getText().toString().isEmpty()){
                    phone.setBackground(AppCompatResources.getDrawable(getApplicationContext(),R.drawable.error_edit_text_border));
                    errorPhoneNumber.setText("Please write your phone number");
                }
                else{
                    errorPhoneNumber.setText("Your phone number");
                    phone.setBackground(AppCompatResources.getDrawable(getApplicationContext(),R.drawable.ok_edit_text_border));
                    flag2[0] = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!phone.getText().toString().isEmpty()){
                    Intent toVerification = new Intent(getApplicationContext(),VerificationActivity.class);
                    toVerification.putExtra("Phone number",phone.getText().toString());
                    startActivity(toVerification);
                }
            }
        });

    }
}
//instagram.setBackground(AppCompatResources.getDrawable(getApplicationContext(),R.drawable.error_edit_text_border));
//                    phone.setBackground(AppCompatResources.getDrawable(getApplicationContext(),R.drawable.error_edit_text_border));