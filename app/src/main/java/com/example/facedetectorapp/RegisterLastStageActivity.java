package com.example.facedetectorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = String.valueOf(pass.getText());
                String confirmPass = String.valueOf(confirmPassword.getText());
                userError.setTextColor(Color.BLACK);
                passError.setTextColor(Color.BLACK);
                isPasswordCorrect.setTextColor(Color.BLACK);
                if (username.getText().toString().equals("") && password.equals("") && confirmPass.equals("")) {
                    userError.setText("Please create new username");
                    userError.setTextColor(Color.RED);
                    passError.setText("Please create password");
                    passError.setTextColor(Color.RED);
                    isPasswordCorrect.setText("Please confirm your password");
                    isPasswordCorrect.setTextColor(Color.RED);
                    Intent registerLastPageIntent = new Intent(
                            getApplicationContext(), RegisterLastStageActivity.class
                    );
                    startActivity(registerLastPageIntent);
                } else if (username.getText().toString().equals("")) {
                    userError.setText("Please create new username");
                    userError.setTextColor(Color.RED);
                } else if (password.equals("")) {
                    passError.setText("Please create password");
                    passError.setTextColor(Color.RED);
                } else if (confirmPass.equals("")) {
                    isPasswordCorrect.setText("Please confirm your password");
                    isPasswordCorrect.setTextColor(Color.RED);
                } else if (!password.equals(confirmPass.toString())) {
                    isPasswordCorrect.setText("Passwords do not match");
                    isPasswordCorrect.setTextColor(Color.RED);
                } else {
                    String user = String.valueOf(username.getText());
                    String pass = String.valueOf(confirmPassword.getText());
                    addDatabaseToFirestore(user,pass);
                    Intent toSearchPage = new Intent(
                            getApplicationContext(), SearchByGeolocationActivity.class
                    );
                    startActivity(toSearchPage);
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