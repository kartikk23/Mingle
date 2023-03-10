package com.kartik.campusBuddy.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.kartik.campusBuddy.Models.QuoteModel;
import com.kartik.campusBuddy.R;
import java.util.ArrayList;

public class QuoteAdapter extends RecyclerView.Adapter<QuoteAdapter.myViewHolder> {

    ArrayList<QuoteModel> list = new ArrayList<>();
    Context context;

    public QuoteAdapter(ArrayList<QuoteModel> list, Context context) {
        this.list = list;
    }

    @NonNull
    @Override
    public QuoteAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quote, parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuoteAdapter.myViewHolder holder, int position) {
        holder.quote.setText(list.get(position).getQuote());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView quote;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            quote = itemView.findViewById(R.id.tv_quote);
        }
    }
}
