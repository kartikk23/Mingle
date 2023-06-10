package com.kartik.campusBuddy.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kartik.campusBuddy.Models.ServiceModel;
import com.kartik.campusBuddy.databinding.ActivityServiceDetailsBinding;

import java.util.HashMap;

public class ServiceDetailsActivity extends AppCompatActivity {
    ActivityServiceDetailsBinding binding;
    FirebaseDatabase database;
    FirebaseUser user;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityServiceDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        user = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Service Details").child(user.getUid());

        String userid = user.getUid();
        String name = binding.etName.getText().toString();
        String price = binding.etPrice.getText().toString();
        


        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(ServiceDetailsActivity.this, name+price, Toast.LENGTH_SHORT).show();
                ServiceModel newService = new ServiceModel(userid, name, price);
                reference.setValue(newService).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(ServiceDetailsActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        

        

    }
}