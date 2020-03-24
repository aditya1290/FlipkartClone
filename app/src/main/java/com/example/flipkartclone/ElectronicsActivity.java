package com.example.flipkartclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.flipkartclone.Models.ProductMobile;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ElectronicsActivity extends AppCompatActivity {

    RecyclerView recyclerview;
    final int ITEM_LOAD_COUNT = 20;
    int total_item = 0,last_visible_item;
    RecyclerViewAdapterMobiles recyclerViewAdapterMobiles;
    boolean isLoading = false, isMaxData = false;

    String last_node="",last_key="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electronics);

        Toolbar toolbar = findViewById(R.id.Electronics_Toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerview = findViewById(R.id.RecycleElectronics);


        getLastKeyFromFirebase();

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(linearLayoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(recyclerview.getContext(), linearLayoutManager.getOrientation());
        recyclerview.addItemDecoration(decoration);

        recyclerViewAdapterMobiles = new RecyclerViewAdapterMobiles(this);
        recyclerview.setAdapter(recyclerViewAdapterMobiles);
        
        getProducts();

        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                total_item = linearLayoutManager.getItemCount();
                last_visible_item = linearLayoutManager.findLastVisibleItemPosition();

                if(!isLoading && total_item<=(last_visible_item + ITEM_LOAD_COUNT))
                {
                    getProducts();
                    isLoading = true;
                }
            }
        });
    }

    private void getProducts() {
        if(!isMaxData)
        {
            Query query;
            if(TextUtils.isEmpty(last_node))
                query = FirebaseDatabase.getInstance().getReference()
                        .child("ProductMobile")
                        .orderByKey()
                        .limitToFirst(ITEM_LOAD_COUNT);
            else
                query = FirebaseDatabase.getInstance().getReference()
                        .child("ProductMobile")
                        .orderByKey()
                        .startAt(last_node)
                        .limitToFirst(ITEM_LOAD_COUNT);

            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if(dataSnapshot.hasChildren())
                    {
                        ArrayList<ProductMobile> productMobiles = new ArrayList<>();
                        for(DataSnapshot userSnapShot:dataSnapshot.getChildren())
                        {
                            productMobiles.add(userSnapShot.getValue(ProductMobile.class));
                        }

                        last_node = productMobiles.get(productMobiles.size()-1).getName();

                        if(!last_node.equals(last_key))
                            productMobiles.remove(productMobiles.size()-1);
                        else
                            last_node = "end";

                        recyclerViewAdapterMobiles.addAll(productMobiles);
                        isLoading = false;
                    }
                    else
                    {
                        isLoading = false;
                        isMaxData = true;
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    isLoading = false;
                    Log.e("Koi na", "ho gya to");
                }
            });


        }

    }

    private void getLastKeyFromFirebase() {

        Query getLastKey = FirebaseDatabase.getInstance().getReference()
                .child("ProductMobiles")
                .orderByKey()
                .limitToLast(1);
        getLastKey.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot lastkey : dataSnapshot.getChildren())
                {
                    lastkey.getKey();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ElectronicsActivity.this, "Cnt get last key", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
