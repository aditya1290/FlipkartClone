package com.example.flipkartclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {

    Button order,review,cart,address;
    TextView lang,notif,acc,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        order = findViewById(R.id.order);
        review = findViewById(R.id.review);
        cart = findViewById(R.id.cart);
        address = findViewById(R.id.address);

        lang = findViewById(R.id.language_setting);
        notif = findViewById(R.id.notification_setting);
        acc = findViewById(R.id.account_setting);
        logout = findViewById(R.id.logout);

        order.setOnClickListener(this);
        review.setOnClickListener(this);
        cart.setOnClickListener(this);
        address.setOnClickListener(this);

        lang.setOnClickListener(this);
        notif.setOnClickListener(this);
        acc.setOnClickListener(this);
        logout.setOnClickListener(this);

    }

    @Override
    public void onClick(View view){
        if(view.getId()== R.id.order) {
            Intent intent1 = new Intent(this, orderActivity.class);
            startActivity(intent1);
        } else if(view.getId()==R.id.review) {
            Intent intent2 = new Intent(this, reviewActivity.class);
            startActivity(intent2);
        } else if(view.getId()==R.id.cart) {
                Intent intent3 = new Intent(this, cartActivity.class);
                startActivity(intent3);
        }else if(view.getId()==R.id.address) {
            Intent intent4 = new Intent(this, addressActivity.class);
            startActivity(intent4);
        }else if(view.getId()==R.id.language_setting) {
            Intent intent5 = new Intent(this, langActivity.class);
            startActivity(intent5);
        }else if(view.getId()==R.id.notification_setting) {
            Intent intent6 = new Intent(this, notifActivity.class);
            startActivity(intent6);
        }else if(view.getId()==R.id.account_setting) {
            Intent intent7 = new Intent(this, accActivity.class);
            startActivity(intent7);
        }else if(view.getId()==R.id.logout) {
            Intent intent8 = new Intent(this, MainActivity.class);
            startActivity(intent8);
        }
    }
}

