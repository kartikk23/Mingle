package com.kartik.campusBuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kartik.campusBuddy.Adapters.UserAdapter;
import com.kartik.campusBuddy.Models.Users;
import com.kartik.campusBuddy.databinding.ActivitySearchBinding;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    ActivitySearchBinding binding;
    FirebaseDatabase database;
    ArrayList<Users> list = new ArrayList<>();
    UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        database = FirebaseDatabase.getInstance();

        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchUser(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchUser(newText);
                return false;
            }
        });

    }

    private void searchUser(String s){
        database.getReference().child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot snapshot1: snapshot.getChildren()){
                    Users users = snapshot1.getValue(Users.class);
                    if (users.getUsername().toLowerCase().contains(s.toLowerCase())) {
                        list.add(users);
                    }
//                    adapter = new UserAdapter(list, getApplicationContext());
                    binding.searchRecView.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
                    binding.searchRecView.setAdapter(adapter);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}