package com.example.flipkartclone;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class ShopFragement extends Fragment {

    SliderLayout sliderLayout;
    DatabaseReference reference;
    Button Addprod;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shop, container, false);

        reference = FirebaseDatabase.getInstance().getReference().child("ImageSliderLink");

        sliderLayout = view.findViewById(R.id.ImageSlider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.WORM);
        sliderLayout.setScrollTimeInSec(2);

        Addprod = view.findViewById(R.id.Personal);
        Addprod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), PersonalFrameActivity.class);
                startActivity(i);
            }
        });


//        Addprod.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sendData();
//            }
//        });

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String x = dataSnapshot.child("-1").getValue().toString();
                int x1 = Integer.valueOf(x);

                for(int i = 0;i<x1;i++)
                {
                    DefaultSliderView sliderView = new DefaultSliderView(getActivity());
                    sliderView.setImageUrl(dataSnapshot.child(String.valueOf(i)).getValue().toString());
                    sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
                    final int finalI = i;
                    sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(SliderView sliderView) {
                            Toast.makeText(getActivity().getApplicationContext(), "You clicked"+(finalI+1), Toast.LENGTH_SHORT).show();
                        }
                    });

                    sliderLayout.addSliderView(sliderView);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        super.onViewCreated(view, savedInstanceState);
    }

    public void sendData()
    {
        Intent i = new Intent(getActivity().getBaseContext(), AddProd.class);
        startActivity(i);
    }

}



