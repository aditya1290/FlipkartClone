package com.example.flipkartclone;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flipkartclone.Models.Orders;

import java.util.ArrayList;

public class OrdersActivityAdapter extends RecyclerView.Adapter<OrdersActivityAdapter.MyViewHolder> {

    Context c;
    ArrayList<Orders>list;

    public OrdersActivityAdapter(Context c, ArrayList<Orders> list) {
        this.c = c;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.card_orders,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.name.setText(list.get(position).getProduct_Name1());
        holder.price.setText(list.get(position).getProduct_Price1());
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.Order_productName);
            price = itemView.findViewById(R.id.Order_productPrice);


        }
    }
}
