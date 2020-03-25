package com.example.flipkartclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
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

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerview;
    final int ITEM_LOAD_COUNT = 15;
    int total_item = 0,last_visible_item;
    RecyclerViewAdapterMobiles recyclerViewAdapterMobiles;
    boolean isLoading = false, isMaxData = false;

    String last_node="",last_key="";
    EditText Search_ediitext;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electronics);

        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        swipeRefreshLayout = findViewById(R.id.SwipeRefreshElectronics);
        recyclerview = findViewById(R.id.RecycleElectronics);

        getLastKeyFromFirebase();

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(linearLayoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(recyclerview.getContext(), linearLayoutManager.getOrientation());
        recyclerview.addItemDecoration(decoration);

        recyclerViewAdapterMobiles = new RecyclerViewAdapterMobiles(this);
        recyclerview.setAdapter(recyclerViewAdapterMobiles);
        
        getProducts();

        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.Red),getResources().getColor(R.color.Blue));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recyclerViewAdapterMobiles.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },3000);
            }
        });

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.electronics_toolbar_button,menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if(id==R.id.search_toolbar)
        {


        }
        if(id==R.id.Cart_toolbar)
        {
            Intent i = new Intent(this, CartActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
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

            final ProgressDialog Dialog = new ProgressDialog(this);
            Dialog.setMessage("Fetching data...");
            Dialog.setCanceledOnTouchOutside(false);
            Dialog.show();
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
                        Dialog.hide();

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
