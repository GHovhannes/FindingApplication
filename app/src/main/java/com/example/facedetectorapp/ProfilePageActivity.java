package com.example.facedetectorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ProfilePageActivity extends AppCompatActivity {
    ImageView toHistoryPage,toSearchPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        toHistoryPage = findViewById(R.id.to_history_page);
        toSearchPage = findViewById(R.id.to_search_page);
        toSearchPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toSearch = new Intent(getApplicationContext(),SearchPageActivity.class);
                startActivity(toSearch);
            }
        });
        toHistoryPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toHistory = new Intent(getApplicationContext(),HistoryPageActivity.class);
                startActivity(toHistory);
            }
        });

    }
}