package com.kartik.campusBuddy.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.kartik.campusBuddy.Models.messageModel;
import com.kartik.campusBuddy.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChatAdapter extends RecyclerView.Adapter {
    ArrayList<messageModel> messageModels;
    Context context;
    String rec_id;

    int SENDER_VIEW_TYPE = 1;
    int RECEIVER_VIEW_TYPE = 2;

    public ChatAdapter(ArrayList<messageModel> messageModels, Context context, String rec_id) {
        this.messageModels = messageModels;
        this.context = context;
        this.rec_id = rec_id;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == SENDER_VIEW_TYPE){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sender_message_layout, parent ,false);
            return new senderViewHolder(view);
        }
        else{
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.receiver_message_layout, parent, false);
            return new receiverViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        messageModel messageModel = messageModels.get(position);
        Long stamp = messageModel.getTimestamp();
        stamp = stamp/1000;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String strDate  = sdf.format(new Date(stamp*1000));
//        String time = Long.toString(stamp);

        if (holder.getClass() == senderViewHolder.class){
                ((senderViewHolder) holder).senderMsg.setText(messageModel.getMessage());
                ((senderViewHolder) holder).sender_time.setText(strDate);
        }
        else {
            ((receiverViewHolder) holder).receiverMsg.setText(messageModel.getMessage());
            ((receiverViewHolder) holder).receiverTime.setText(strDate);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (messageModels.get(position).getUid().equals(FirebaseAuth.getInstance().getUid())){
            return SENDER_VIEW_TYPE;
        }
        else{
            return RECEIVER_VIEW_TYPE;
        }
    }

    @Override
    public int getItemCount() {
        return messageModels.size();
    }
    public class receiverViewHolder extends RecyclerView.ViewHolder{
        TextView receiverMsg;
        TextView receiverTime;
        public receiverViewHolder(@NonNull View itemView) {
            super(itemView);
//            receiverMsg = itemView.findViewById(R.id.receiverText);
//            receiverTime = itemView.findViewById(R.id.rec_time);
        }
    }

    public class senderViewHolder extends RecyclerView.ViewHolder{
        TextView senderMsg;
        TextView sender_time;

        public senderViewHolder(@NonNull View itemView) {
            super(itemView);
//            senderMsg = itemView.findViewById(R.id.senderText);
//            sender_time = itemView.findViewById(R.id.sender_time);
        }
    }
}