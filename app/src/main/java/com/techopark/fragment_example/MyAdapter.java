package com.techopark.fragment_example;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<Integer> list;
    private OnRecyclerViewItemListener itemListener;

    MyAdapter(ArrayList<Integer> list, OnRecyclerViewItemListener itemListener) {
        this.list = list;
        this.itemListener = itemListener;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.count);
        }

        void bindClickListener(int position, OnRecyclerViewItemListener itemListener) {
            itemView.findViewById(R.id.count).setOnClickListener(v -> {
                        Toast.makeText(itemView.getContext(), "Clicked on item with pos " + (position + 1), Toast.LENGTH_SHORT).show();
                        itemListener.onClick(textView);
                    }
            );
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (position % 2 == 0)
            holder.textView.setTextColor(Color.BLUE);
        else
            holder.textView.setTextColor(Color.RED);
        holder.textView.setText(list.get(position).toString());
        holder.bindClickListener(position, itemListener);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}
