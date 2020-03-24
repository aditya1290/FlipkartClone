package com.example.flipkartclone;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.flipkartclone.Models.ProductMobile;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterMobiles extends RecyclerView.Adapter<RecyclerViewAdapterMobiles.MyViewHolder> {

    public RecyclerViewAdapterMobiles(Context c) {
        this.c = c;
        this.productMobileList = new ArrayList<>();
    }

    private Context c;
    private ArrayList<ProductMobile> productMobileList;


    public void addAll(ArrayList<ProductMobile>PM)
    {
        int initsize = PM.size();
        productMobileList.addAll(PM);
        notifyItemRangeChanged(initsize,PM.size());
    }
    public String getLastItemId()
    {
        return productMobileList.get(productMobileList.size()-1).getName();
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        LayoutInflater inflater = LayoutInflater.from(c);
        v = inflater.inflate(R.layout.electronics_cardview,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        String str = productMobileList.get(position).getURL().toString();

        Glide.with(c)
                .load(new File(str))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.Phone_image);

        holder.Mobile_name.setText(productMobileList.get(position).getName());
        int s5 = productMobileList.get(position).getStar5();
        int s4 = productMobileList.get(position).getStar4();
        int s3 = productMobileList.get(position).getStar3();
        int s2 = productMobileList.get(position).getStar2();
        int s1 = productMobileList.get(position).getStar1();
        double s = (s5*5+s4*4+s3*3+s2*2+s1)/(s1+s2+s3+s4+s5);
        holder.Rating_Mobile.setText(String.valueOf(s));
        int price = Integer.valueOf(productMobileList.get(position).getPrice());
        String only = "Rs. "+String.valueOf(price);
        holder.OldPrice.setText(only);
        int x = productMobileList.get(position).getOffMobile();
        String pr = String.valueOf(x) + "% Off";
        holder.OffMobiles.setText(pr);
        pr = String.valueOf(price*x/100);
        holder.NewPrice.setText(pr);





    }

    @Override
    public int getItemCount() {
        return productMobileList.size();
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder
    {

        ImageView Phone_image;
        TextView Mobile_name;
        TextView Rating_Mobile;
        TextView NewPrice;
        TextView OldPrice, OffMobiles;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Phone_image = itemView.findViewById(R.id.Phone_image);
            Mobile_name = itemView.findViewById(R.id.Mobile_name);
            Rating_Mobile = itemView.findViewById(R.id.Rating_Mobile);
            NewPrice = itemView.findViewById(R.id.NewPrice);
            OldPrice = itemView.findViewById(R.id.OldPrice);
            OffMobiles = itemView.findViewById(R.id.OffMobiles);
        }
    }

}
