package com.kartik.campusBuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
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
import com.google.firebase.messaging.FirebaseMessaging;
import com.kartik.campusBuddy.Adapters.OnItemClick;
import com.kartik.campusBuddy.Adapters.UserAdapter;
import com.kartik.campusBuddy.Models.ChatList;
import com.kartik.campusBuddy.Models.Users;
import com.kartik.campusBuddy.Notifications.Token;
import com.kartik.campusBuddy.databinding.ActivityRecentChatsBinding;

import java.util.ArrayList;

public class RecentChatsActivity extends AppCompatActivity {

    ActivityRecentChatsBinding binding;
    Typeface MR, MRR;
    private UserAdapter userAdapter;
    private ArrayList<Users> mUsers;
    FirebaseUser fuser;
    FrameLayout frameLayout;
    DatabaseReference reference;
    private ArrayList<ChatList> usersList;
    static OnItemClick onItemClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecentChatsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MRR = Typeface.createFromAsset(this.getAssets(), "fonts/myriadregular.ttf");
        MR = Typeface.createFromAsset(this.getAssets(), "fonts/myriad.ttf");

//        recyclerView = view.findViewById(R.id.recycler_view);
        frameLayout = findViewById(R.id.es_layout);
//        es_descp = view.findViewById(R.id.es_descp);
//        es_title = view.findViewById(R.id.es_title);

//        binding..setTypeface(MR);
//        es_title.setTypeface(MRR);

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        binding.recyclerView.addItemDecoration(dividerItemDecoration);


        fuser = FirebaseAuth.getInstance().getCurrentUser();

        usersList = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("Chatlist").child(fuser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                usersList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ChatList chatlist = snapshot.getValue(ChatList.class);
                    usersList.add(chatlist);
                }
                if (usersList.size() == 0) {
                    frameLayout.setVisibility(View.VISIBLE);
                } else {
                    frameLayout.setVisibility(View.GONE);
                }

                chatList();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (!task.isSuccessful()){
                    Toast.makeText(RecentChatsActivity.this, "unsuccessful", Toast.LENGTH_SHORT).show();
                }
                updateToken(task.getResult());
            }
        });

    }

    private void updateToken(String token) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Tokens");
        Token token1 = new Token(token);
        reference.child(fuser.getUid()).setValue(token1);
    }

    private void chatList() {
        mUsers = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUsers.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Users user = snapshot.getValue(Users.class);
                    for (ChatList chatlist : usersList) {
                        if (user != null && user.getId() != null && chatlist != null && chatlist.getId() != null &&
                                user.getId().equals(chatlist.getId())) {
                            mUsers.add(user);
                        }
                    }
                }
                userAdapter = new UserAdapter(RecentChatsActivity.this, onItemClick, mUsers, true);
                binding.recyclerView.setAdapter(userAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}