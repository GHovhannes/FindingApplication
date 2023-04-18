package com.example.facedetectorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button register;
    TextView usAg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        register = findViewById(R.id.to_verification_stage);
        usAg = findViewById(R.id.to_agreement);

        usAg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent agreementIntent = new Intent(
                        getApplicationContext(), UsersAgreementActivity.class
                );
                startActivity(agreementIntent);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(
                        getApplicationContext(), RegisterActivity.class
                );
                startActivity(registerIntent);
            }
        });
    }
}