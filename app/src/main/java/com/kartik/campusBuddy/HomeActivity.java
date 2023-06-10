package com.kartik.campusBuddy;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import com.kartik.campusBuddy.databinding.ActivityHomeBinding;
import com.kartik.campusBuddy.views.FitnessActivity;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.fitness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, FitnessActivity.class));
            }
        });


        SharedPreferences prefs = getSharedPreferences("myPrefs", MODE_PRIVATE);
        String lastActivity = prefs.getString("lastActivity", null);

        if (lastActivity != null) {
            try {
                Class<?> activityClass = Class.forName(lastActivity);
                Intent intent = new Intent(this, activityClass);
                startActivity(intent);
            } catch (ClassNotFoundException e) {
                // Handle exception
            }
        } else {
            // If no last activity found, start default activity
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}