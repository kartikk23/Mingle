package com.kartik.campusBuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kartik.campusBuddy.Adapters.SwipeAdapter;
import com.kartik.campusBuddy.Models.Users;
import com.yalantis.library.Koloda;

import java.util.ArrayList;

public class SwipeActivity extends AppCompatActivity {
    private SwipeAdapter swipeAdapter;
    private ArrayList<Users> list = new ArrayList<>();
    Koloda koloda;
    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);
        database = FirebaseDatabase.getInstance();
        koloda = findViewById(R.id.koloda);
        swipeAdapter = new SwipeAdapter(this, list);
        koloda.setAdapter(swipeAdapter);




        // CODE FOR DISPLAYING LIST OF USERS IN ANDROID APPLICATION...

        database.getReference().child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot snapshot1: snapshot.getChildren()){
                    Users users = snapshot1.getValue(Users.class);
//                    users.getId(snapshot1.getKey());
                    if(!users.getId().equals(FirebaseAuth.getInstance().getUid())) {
                        list.add(users);
                    }
                }
                swipeAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}