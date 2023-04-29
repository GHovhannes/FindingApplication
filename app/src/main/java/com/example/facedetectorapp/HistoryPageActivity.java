package com.example.facedetectorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class HistoryPageActivity extends AppCompatActivity {
    ImageView toProfilePage,toSearchPage;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_page);
        toProfilePage = findViewById(R.id.to_profile_page);
        toSearchPage = findViewById(R.id.to_search_page);
        recyclerView = findViewById(R.id.user_list);
        recyclerView.setHasFixedSize(true);
        swipeRefreshLayout  = findViewById(R.id.swipe_refresh_layout);

        setupSwipeRefreshLayout();
        getDatabase();

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

    public void setupSwipeRefreshLayout() {
        swipeRefreshLayout  = findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDatabase();
            }
        });
    }
    public void getDatabase(){
        ArrayList<User> userList = new ArrayList<>();
        FirebaseFirestore data = FirebaseFirestore.getInstance();
        data.collection("Users").get().addOnCompleteListener(task -> {

            for (QueryDocumentSnapshot dataSnapshot: task.getResult()){

                User user = new User();
                user.instagramNickname = dataSnapshot.getString("Username");
                user.phoneNumber = dataSnapshot.getString("Phone Number");
                userList.add(user);
            }
            if (userList.size() > 0) {
                adapter = new UserAdapter(this, userList);
                recyclerView.setAdapter(adapter);
            }
        });
    }
}