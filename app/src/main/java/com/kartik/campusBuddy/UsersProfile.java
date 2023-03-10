package com.kartik.campusBuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.kartik.campusBuddy.databinding.ActivityUsersBinding;
import com.kartik.campusBuddy.databinding.ActivityUsersProfileBinding;

public class UsersProfile extends AppCompatActivity {
    ActivityUsersProfileBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUsersProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        String userName = getIntent().getStringExtra("userName");
        String uid = getIntent().getStringExtra("userId");
        binding.tvUserName.setText(userName);
        binding.btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UsersProfile.this, ChatActivity.class);
                i.putExtra("userId", uid);
                i.putExtra("userName", userName);
                startActivity(i);
            }
        });
    }
}