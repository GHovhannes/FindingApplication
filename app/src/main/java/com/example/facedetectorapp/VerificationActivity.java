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

public class VerificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        EditText verify = findViewById(R.id.verification_code);
        TextView submit = findViewById(R.id.to_registration_last_stage);
        TextView verifyText = findViewById(R.id.verification_code_warning);
        final boolean flag[] = {false};

        verify.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String verifyCode = String.valueOf(verify.getText());
                if (verifyCode.equals("4")) {
                    verify.setBackground(AppCompatResources.getDrawable(getApplicationContext(), R.drawable.ok_edit_text_border));
                    flag[0] = true;
                } else if (verifyCode.isEmpty() || !verifyCode.equals("4")) {
                    verify.setBackground(AppCompatResources.getDrawable(getApplicationContext(), R.drawable.error_edit_text_border));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            Intent pn = getIntent();
            String phoneNumber = pn.getStringExtra("Phone number");
            @Override
            public void onClick(View v) {
                if (flag[0]) {
                    Intent toLastStage = new Intent(getApplicationContext(), RegisterLastStageActivity.class);
                    toLastStage.putExtra("Phone number",phoneNumber);
                    startActivity(toLastStage);
                }
            }
        });
    }
}