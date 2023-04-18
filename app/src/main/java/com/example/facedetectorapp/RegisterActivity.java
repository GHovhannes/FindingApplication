package com.example.facedetectorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
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
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Editable instagramLink = (instagram.getText());
                Editable phoneNumber = phone.getText();
                Intent sendLink = new Intent(getApplicationContext(),RegisterLastStageActivity.class);
                sendLink.putExtra("Instagram Link",instagramLink.toString());
                if(instagramLink.toString().equals("") && phoneNumber.toString().equals("")) {
                    errorLink.setText("ERROR");
                    errorLink.setTextColor(Color.RED);
                    errorPhoneNumber.setText("ERROR");
                    errorPhoneNumber.setTextColor(Color.RED);
                    Intent registerIntent = new Intent(
                            getApplicationContext(), RegisterActivity.class
                    );
                    startActivity(registerIntent);
                } else if (phoneNumber.toString().equals("")) {
                    errorPhoneNumber.setText("ERROR");
                    errorPhoneNumber.setTextColor(Color.RED);
                    Intent registerIntent = new Intent(
                            getApplicationContext(), RegisterActivity.class
                    );
                    startActivity(registerIntent);

                } else if (instagramLink.toString().equals("")) {
                    errorLink.setText("ERROR");
                    errorLink.setTextColor(Color.RED);
                    Intent registerIntent = new Intent(
                            getApplicationContext(), RegisterActivity.class
                    );
                    startActivity(registerIntent);

                } else{
                    Intent verifyIntent = new Intent(
                            getApplicationContext(), VerificationActivity.class
                    );
                    startActivity(verifyIntent);
                }
            }
        });

    }
}