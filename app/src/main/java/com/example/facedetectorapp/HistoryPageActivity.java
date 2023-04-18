package com.example.facedetectorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HistoryPageActivity extends AppCompatActivity {
    ImageView toProfilePage,toSearchPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_page);
        toProfilePage = findViewById(R.id.to_profile_page);
        toSearchPage = findViewById(R.id.to_search_page);
        toProfilePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toProfile = new Intent(getApplicationContext(),ProfilePageActivity.class);
                startActivity(toProfile);
            }
        });
        toSearchPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toSearch = new Intent(getApplicationContext(),SearchPageActivity.class);
                startActivity(toSearch);
            }
        });
    }
}