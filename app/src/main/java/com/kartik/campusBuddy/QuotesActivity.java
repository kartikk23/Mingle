package com.kartik.campusBuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.kartik.campusBuddy.Adapters.QuoteAdapter;
import com.kartik.campusBuddy.Models.QuoteModel;
import com.kartik.campusBuddy.databinding.ActivityQuotesBinding;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class QuotesActivity extends AppCompatActivity {
    ArrayList<QuoteModel> list = new ArrayList<>();
    ActivityQuotesBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.recView.setLayoutManager(new LinearLayoutManager(this));
        binding.recView.setHasFixedSize(true);
        getQuote();

    }

    private void getQuote(){
        String url = "https://zenquotes.io/api/quotes";
        RequestQueue queue = Volley.newRequestQueue(QuotesActivity.this);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<response.length();i++) {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        String quote = object.getString("q");
                        list.add(new QuoteModel(quote));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    QuoteAdapter adapter = new QuoteAdapter(list, getApplicationContext());
                    binding.recView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(QuotesActivity.this, "Quotes fetched Successfully!", Toast.LENGTH_SHORT).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(QuotesActivity.this, "Failed to fetch quotes!", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }


}