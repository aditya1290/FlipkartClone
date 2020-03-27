package com.example.flipkartclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.ContactsContract;

import com.example.flipkartclone.Models.Cart;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    DatabaseReference reference;
    FirebaseUser user;
    ArrayList<Cart>list;
    RecyclerView recyclerView;
    CardAdapter cardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference();
        list = new ArrayList<Cart>();

        recyclerView = findViewById(R.id.Rec2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        reference.child("User").child(user.getUid()).child("CartMain").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                int x = Integer.valueOf(dataSnapshot.child("-1").getValue().toString());
                for(int i =0; i<x;i++)
                {
                    Cart cart = new Cart();
                    cart = dataSnapshot.child(String.valueOf(i)).getValue(Cart.class);
                    list.add(cart);
                }

                cardAdapter = new CardAdapter(getApplicationContext(),list);
                recyclerView.setAdapter(cardAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}
