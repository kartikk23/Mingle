package com.kartik.campusBuddy.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kartik.campusBuddy.ChatActivity;
import com.kartik.campusBuddy.Models.Users;
import com.kartik.campusBuddy.R;

import java.util.ArrayList;

public class SwipeAdapter extends BaseAdapter {
    Context context;
    ArrayList<Users> list;

    public SwipeAdapter(Context context, ArrayList<Users> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView==null){
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card,parent, false);
        }
        ((TextView) view.findViewById(R.id.cardName)).setText(list.get(i).getUsername());
        view.findViewById(R.id.chat_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), ChatActivity.class);
                view.getContext().startActivity(i);
            }
        });
        return view;
    }
}
