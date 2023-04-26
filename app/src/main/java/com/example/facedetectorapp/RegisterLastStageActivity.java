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
import android.widget.Toast;

//import com.google.firebase.firestore.FirebaseFirestore;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class RegisterLastStageActivity extends AppCompatActivity {

    EditText pass;
    EditText username;
    TextView userError;
    EditText confirmPassword;
    TextView isPasswordCorrect;
    TextView submit;
    TextView passError;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_last_stage);
        pass = findViewById(R.id.create_password);
        username = findViewById(R.id.create_username);
        userError = findViewById(R.id.create_username_warning);
        confirmPassword = findViewById(R.id.confirm_password);
        isPasswordCorrect = findViewById(R.id.confirm_password_warning);
        submit = findViewById(R.id.submit);
        passError = findViewById(R.id.create_password_warning);
        final boolean[] flag1 = {false};
        final boolean[] flag2 = {false};
        final boolean[] flag3 = {false};

        pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(pass.getText().toString().isEmpty()){
                    pass.setBackground(AppCompatResources.getDrawable(getApplicationContext(),R.drawable.error_edit_text_border));
                    passError.setText("Please crate a password");
                }
                else{
                    passError.setText("Create password");
                    pass.setBackground(AppCompatResources.getDrawable(getApplicationContext(),R.drawable.ok_edit_text_border));
                    flag1[0] = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

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
                    userError.setText("Please create a username");
                }
                else{
                    userError.setText("Create username");
                    username.setBackground(AppCompatResources.getDrawable(getApplicationContext(),R.drawable.ok_edit_text_border));
                    flag2[0] = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        confirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(confirmPassword.getText().toString().isEmpty()){
                    confirmPassword.setBackground(AppCompatResources.getDrawable(getApplicationContext(),R.drawable.error_edit_text_border));
                    isPasswordCorrect.setText("Please confirm your password");
                }
                else{
                    confirmPassword.setBackground(AppCompatResources.getDrawable(getApplicationContext(),R.drawable.ok_edit_text_border));
                    flag3[0] = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!pass.getText().toString().isEmpty()
                        &&!username.getText().toString().isEmpty()
                        &&!confirmPassword.getText().toString().isEmpty()
                        &&confirmPassword.getText().toString().equals(pass.getText().toString())) {
                    addDatabaseToFirestore(username.getText().toString(),confirmPassword.getText().toString());
                    Intent toSearch = new Intent(getApplicationContext(),SearchPageActivity.class);
                    startActivity(toSearch);
                }else if(!confirmPassword.getText().toString().equals(pass.getText().toString())){
                    isPasswordCorrect.setText("Passwords do not match");
                    isPasswordCorrect.setTextColor(Color.RED);
                    passError.setTextColor(Color.RED);
                    passError.setText("Passwords do not match");
                    confirmPassword.setBackground(AppCompatResources.getDrawable(getApplicationContext(),R.drawable.error_edit_text_border));
                    pass.setBackground(AppCompatResources.getDrawable(getApplicationContext(),R.drawable.error_edit_text_border));
                }
            }
        });
    }
    public void addDatabaseToFirestore(String username, String password){
        Intent receiveLink = getIntent();
        String instagramLink = receiveLink.getStringExtra("Instagram Link");
        FirebaseFirestore data = FirebaseFirestore.getInstance();
        HashMap<String, Object> user = new HashMap<>();
        user.put("Username",username);
        user.put("Instagram Link",instagramLink);
        user.put("Password",password);
        data.collection("user")
                .add(user)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(getApplicationContext(), "Data inserted", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(exception -> {
                    Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}