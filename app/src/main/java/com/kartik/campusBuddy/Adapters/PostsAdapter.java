package com.kartik.campusBuddy.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.kartik.campusBuddy.Models.Posts;
import com.kartik.campusBuddy.R;


import java.util.ArrayList;


public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    ArrayList<Posts> list = new ArrayList<>();
    Context context;


    public PostsAdapter(ArrayList<Posts> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public PostsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_posts, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsAdapter.ViewHolder holder, int position) {
        Posts post = list.get(position);
        holder.userName.setText(post.getUserName());
        Glide.with(context).load(post.getImageUrl()).into(holder.post);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView userName;
        ImageView post;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.userName);
            post = itemView.findViewById(R.id.post);
        }
    }
}
