package com.kartik.campusBuddy;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

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
import com.kartik.campusBuddy.Models.Users;
import com.kartik.campusBuddy.QuotesPack.Notification_Reciever;
import com.kartik.campusBuddy.databinding.ActivityMainBinding;
import com.kartik.campusBuddy.views.NewServiceActivity;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import org.json.JSONObject;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements PaymentResultListener {


    private ActivityMainBinding binding;
    FirebaseAuth auth;
    String token;
    FirebaseDatabase database;
    DatabaseReference reference;
    String accountName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();

        FirebaseUser user = auth.getCurrentUser();
        binding.tvUserName.setText(user.getDisplayName());
        setSupportActionBar(binding.toolbar);



        binding.btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
            }
        });

        binding.btnAddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NewServiceActivity.class));
            }
        });


        binding.btnSwipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SwipeActivity.class);
                startActivity(i);
            }
        });
        binding.btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, PostActivity.class);
                i.putExtra("userName",accountName);
                startActivity(i);
            }
        });

//        Method for keeping the user signed in


        reference = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users user = snapshot.getValue(Users.class);
                accountName = user.getUsername();
                binding.tvUserName.setText(accountName);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (!task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "not done", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(MainActivity.this, task.getResult(), Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(i);
            }
        });




        binding.btnQuotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    getNotification();
                }
                Intent i = new Intent(MainActivity.this, QuotesActivity.class);
                startActivity(i);
            }
        });

        binding.btnRecentChats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, RecentChatsActivity.class);
                startActivity(i);
            }
        });


        binding.btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPayment();
            }
        });

        binding.btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
        binding.btnShowUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, UsersActivity.class);
                startActivity(i);
            }
        });
    }


    private void startPayment() {
        String samount = binding.etAmount.getText().toString();
        int amount = Math.round(Float.parseFloat(samount) * 100);
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_RNtGQoKIOkMgHj");
        checkout.setImage(R.drawable.ic_facebook);

        final Activity activity = this;


        try {
            JSONObject options = new JSONObject();

            options.put("name", "Agile Developers");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
//            options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", amount);//pass amount in currency subunits
            options.put("prefill.email", "kartikkhobragade88@gmail.com");
            options.put("prefill.contact", "7721915955");
            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            checkout.open(activity, options);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this, "Payment Successful!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Payment Failed!", Toast.LENGTH_SHORT).show();
    }


    @RequiresApi(api = Build.VERSION_CODES.M)


    private void getNotification() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 16);
        calendar.set(Calendar.MINUTE, 41);
        calendar.set(Calendar.SECOND, 0);
        Intent i = new Intent(getApplicationContext(), Notification_Reciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, i, PendingIntent.FLAG_IMMUTABLE);
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        Toast.makeText(this, "Notifying...", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onBackPressed() {

        finishAffinity();
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
//        finish();

    }



    // code to save app instance OR restore the previous activtiy

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences prefs = getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("lastActivity", getClass().getName());
        editor.apply();
    }

}