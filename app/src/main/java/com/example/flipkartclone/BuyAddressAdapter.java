package com.example.flipkartclone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flipkartclone.Models.DAddress;

import java.util.ArrayList;

public class BuyAddressAdapter extends RecyclerView.Adapter<BuyAddressAdapter.ViewHolder> {

    private Context c;
    private  ArrayList<DAddress> d_address;
    private RadioButton rb1;


    public BuyAddressAdapter(Context c, ArrayList<DAddress>list) {
        this.c = c;
        this.d_address = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(c).inflate(R.layout.buy_address_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final BuyAddressAdapter.ViewHolder holder, final int position) {

        String string = d_address.get(position).getAddress().toString()+d_address.get(position).getCity().toString()+d_address.get(position).getPincode().toString() ;
        holder.radioButton.setText(string);
        if(position==1){
            holder.radioButton.setChecked(true);
            rb1 = holder.radioButton;
        }

        holder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.radioButton.setChecked(true);
                rb1.setChecked(false);
                rb1 = holder.radioButton;
            }
        });




    }

    @Override
    public int getItemCount() {
        return d_address.size();
    }

    public static class  ViewHolder extends RecyclerView.ViewHolder
    {

        RadioButton radioButton;
        CardView buy_card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            radioButton = itemView.findViewById(R.id.buy_card_radio);
            buy_card = itemView.findViewById(R.id.buy_card);


        }
    }

}
