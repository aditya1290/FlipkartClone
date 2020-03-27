package com.example.flipkartclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.flipkartclone.Models.DAddress;
import com.example.flipkartclone.Models.Orders;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BuyActivity extends AppCompatActivity {


    BuyAddressAdapter adapter;
    FirebaseUser user;
    Button BuyProduct;
    ArrayList<DAddress> list;
    int count,x;
    String Price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        Intent i = getIntent();
       final String name = i.getStringExtra("ProductName");
       Log.e("Aditya",name);

        BuyProduct = findViewById(R.id.BUY_PRODUCT);

        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        user = FirebaseAuth.getInstance().getCurrentUser();

        final RecyclerView recyclerview = findViewById(R.id.rec1);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<DAddress>();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                count = Integer.valueOf(dataSnapshot.child("ProductMobile").child(name).child("count").getValue().toString());
                x = Integer.valueOf(dataSnapshot.child("User").child(user.getUid()).child("OrdersMain").child("-1").getValue().toString());
                Price = dataSnapshot.child("ProductMobile").child(name).child("price").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        reference.child("User").child(user.getUid()).child("AddressMain").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                int x = Integer.valueOf(dataSnapshot.child("-1").getValue().toString());
                Log.e("4","aaya");

                for(int i=0;i<x;i++)
                {
                    DAddress d = dataSnapshot.child(String.valueOf(i)).getValue(DAddress.class);
                    list.add(d);
                }
                adapter = new BuyAddressAdapter(BuyActivity.this, list);
                recyclerview.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        BuyProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(count>0)
                {

                    Orders orders = new Orders(name,Price);
                    reference.child("ProductMobile").child(name).child("count").setValue(count-1);
                    reference.child("User").child(user.getUid()).child("OrdersMain").child(String.valueOf(x)).setValue(orders);
                    reference.child("User").child(user.getUid()).child("OrdersMain").child("-1").setValue(x+1);
                }

            }
        });


    }
}
