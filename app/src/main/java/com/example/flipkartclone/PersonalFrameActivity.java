package com.example.flipkartclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.flipkartclone.Models.ProductMobile;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class PersonalFrameActivity extends AppCompatActivity {


    DatabaseReference reference;
    FirebaseUser user;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        Intent intent = getIntent();
        final String name = intent.getStringExtra("ProductID");

        //Lets Start
        final ImageView imageView = findViewById(R.id.Product_image);
        final TextView Product_Name = findViewById(R.id.Product_Name);
        final TextView Rating_Product = findViewById(R.id.Rating_Product);
        final TextView NoUsers = findViewById(R.id.NoUsers);
        TextView Discount = findViewById(R.id.Discount);
        final TextView OutOfStock =findViewById(R.id.OutOfStock);
        final TextView Product_price = findViewById(R.id.Product_Price);
        final TextView Product_OldPrice = findViewById(R.id.OldPrice1);
        final TextView OfferCashPer = findViewById(R.id.OffCashPer);
        final TextView offerAvail = findViewById(R.id.Offervail);
        ImageView hideHighlights = findViewById(R.id.HideHighlights);
        final TextView Battery_output = findViewById(R.id.Battery_output);
        final TextView Processor_output = findViewById(R.id.Processor_output);
        final TextView FrontCam = findViewById(R.id.FrontCam_output);
        final TextView BackCam = findViewById(R.id.BackCam_output);
        final TextView ScreenSize = findViewById(R.id.ScreenSize1);
        final TextView Display = findViewById(R.id.Display_output);
        final TextView RAM = findViewById(R.id.RAM_output);
        final TextView ROM = findViewById(R.id.ROM_display);
        final TextView Expand = findViewById(R.id.Expanded_output);
        final TextView RatingStar = findViewById(R.id.RatingStar_output);
        final TextView users_output = findViewById(R.id.Users_output);


        reference = FirebaseDatabase.getInstance().getReference();

        reference.child("ProductMobile").child(name).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                ScreenSize.setText(dataSnapshot.child("screenSize").getValue().toString());
                Display.setText(dataSnapshot.child("display").getValue().toString());
                RAM.setText(dataSnapshot.child("ram").getValue().toString());
                ROM.setText(dataSnapshot.child("rom").getValue().toString());
                Expand.setText(dataSnapshot.child("expanded").getValue().toString());
                Battery_output.setText(dataSnapshot.child("battery").getValue().toString());
                BackCam.setText(dataSnapshot.child("backCam").getValue().toString());
                FrontCam.setText(dataSnapshot.child("frontCam").getValue().toString());
                int z = Integer.valueOf(dataSnapshot.child("offMobile").getValue().toString());
                int a = Integer.valueOf(dataSnapshot.child("price").getValue().toString());
                Product_price.setText(String.valueOf(a*(100-z)/100));
                OfferCashPer.setText(dataSnapshot.child("offMobile").getValue().toString());
                Product_OldPrice.setText(dataSnapshot.child("price").getValue().toString());

                int  x= Integer.valueOf(dataSnapshot.child("count").getValue().toString());
                if(x<=0)
                    OutOfStock.setAlpha(1);

                Processor_output.setText(dataSnapshot.child("processor").getValue().toString());

                String url = dataSnapshot.child("url").getValue().toString();
                Glide.with(getApplicationContext()).load(url).into(imageView);
                Product_Name.setText(name);
                int star5,star4,star3,star2,star1;
                star5 = Integer.valueOf(dataSnapshot.child("star5").getValue().toString());
                star4 = Integer.valueOf(dataSnapshot.child("star4").getValue().toString());
                star3 = Integer.valueOf(dataSnapshot.child("star3").getValue().toString());
                star2 = Integer.valueOf(dataSnapshot.child("star2").getValue().toString());
                star1 = Integer.valueOf(dataSnapshot.child("star1").getValue().toString());
                if(star1 +star2 +star3 +star4 +star5==0)
                {
                    Rating_Product.setText("NAN");
                    RatingStar.setText("NAN");
                }
                else
                {
                    String za = String.valueOf((double)(5*star5+4*star4+3*star3+2*star2+star1)/(star1+star2+star3+star4+star5));
                    Rating_Product.setText(za);
                    RatingStar.setText(za);
                }
                String x1 = String.valueOf(star1+star2+star3+star4+star5);
                NoUsers.setText(x1);
                users_output.setText(x1);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
