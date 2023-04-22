package com.example.facedetectorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        EditText instagram = findViewById(R.id.instagram_link);
        TextView errorLink = findViewById(R.id.instagram_link_warning);
        TextView next = findViewById(R.id.to_verification_stage);
        TextView errorPhoneNumber = findViewById(R.id.phone_number_warning);
        EditText phone = findViewById(R.id.phone_number);
        final boolean[] flag1 = {false};
        final boolean[] flag2 = {false};

        instagram.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(instagram.getText().toString().isEmpty()){
                    instagram.setBackground(AppCompatResources.getDrawable(getApplicationContext(),R.drawable.error_edit_text_border));
                    errorLink.setText("Please write your instagram nickname");
                }
                else{
                    errorLink.setText("Your Instagram Nickname");
                    instagram.setBackground(AppCompatResources.getDrawable(getApplicationContext(),R.drawable.ok_edit_text_border));
                    flag1[0] = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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
                if(!instagram.getText().toString().isEmpty()&&!phone.getText().toString().isEmpty()){
                    Intent sendLink = new Intent(getApplicationContext(),RegisterLastStageActivity.class);
                    sendLink.putExtra("Instagram Link",instagram.getText().toString());
                    Intent toVerification = new Intent(getApplicationContext(),VerificationActivity.class);
                    startActivity(toVerification);
                }
            }
        });

    }
}
//instagram.setBackground(AppCompatResources.getDrawable(getApplicationContext(),R.drawable.error_edit_text_border));
//                    phone.setBackground(AppCompatResources.getDrawable(getApplicationContext(),R.drawable.error_edit_text_border));