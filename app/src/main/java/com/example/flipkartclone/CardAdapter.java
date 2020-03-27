package com.example.flipkartclone;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flipkartclone.Models.Cart;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder> {


    Context c;
    ArrayList<Cart>arrayList;


    public CardAdapter(Context c, ArrayList<Cart> arrayList) {
        this.c = c;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(c).inflate(R.layout.card_cart,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        String str;
        holder.model.setText(arrayList.get(position).getProduct_Name());
        holder.company.setText(arrayList.get(position).getCompany_name());
        Glide.with(c).load(arrayList.get(position).getImageURL12()).into(holder.image);
        if(Integer.valueOf(arrayList.get(position).getQuantity())>0)
        {
            str = "Rs. "+arrayList.get(position).getPrice();
            holder.price.setText(str);
        }
        else {
            str = "OUT OF STOCK";
            holder.price.setText(str);
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class  MyViewHolder extends RecyclerView.ViewHolder{

        TextView model, company, price;
        ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            model = itemView.findViewById(R.id.Cart_model);
            image = itemView.findViewById(R.id.Cart_image);
            company = itemView.findViewById(R.id.Cart_company);
            price = itemView.findViewById(R.id.Cart_Price);


        }
    }
}
