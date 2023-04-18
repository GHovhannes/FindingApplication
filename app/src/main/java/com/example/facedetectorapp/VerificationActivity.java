package com.example.facedetectorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class VerificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        EditText verify = findViewById(R.id.verification_code);
        TextView submit = findViewById(R.id.to_registration_last_stage);
        TextView verifyText = findViewById(R.id.verification_code_warning);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String verifyCode = String.valueOf(verify.getText());
                if (verifyCode.equals("4")){
                    verifyText.setText("Verification code is right");
                    verifyText.setTextColor(Color.GREEN);
                    Intent registerLastPageIntent = new Intent(
                            getApplicationContext(), RegisterLastStageActivity.class
                    );
                    startActivity(registerLastPageIntent);
                }else {
                    verifyText.setText("Verification code is wrong");
                    verifyText.setTextColor(Color.RED);
                    Intent reVerify = new Intent(
                            getApplicationContext(), VerificationActivity.class
                    );
                    startActivity(reVerify);
                }
            }
        });
    }
}