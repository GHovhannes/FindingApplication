package com.example.facedetectorapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    Context context;
    ArrayList<User> list;


    public UserAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public UserAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_item,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserViewHolder holder, int position) {
        User user = list.get(position);
        holder.instagramNickname.setText(user.getInstagramNickname());
        holder.phoneNumber.setText(user.getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder{


        TextView instagramNickname;
        TextView phoneNumber;


        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            instagramNickname = itemView.findViewById(R.id.instagram_link);
            phoneNumber = itemView.findViewById(R.id.phone_number);
        }
    }
}
