package com.example.flipkartclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.flipkartclone.Models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class IntroActivity extends AppCompatActivity {


    Button login_show_sheet,register_show_sheet;
    Button Login, Register;
    RelativeLayout RR12;

    EditText LoginEmail, LoginPass,RegisterEmail, RegisterName, RegisterPass, RegisterPhone;

    FirebaseAuth auth;
    DatabaseReference reference;
    FirebaseUser user;

    int userid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        RR12 = findViewById(R.id.RR1);
        auth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference();

        reference.child("UserID").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userid = Integer.valueOf(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        getWindow().setStatusBarColor(getResources().getColor(R.color.IntroActivityBlack));

        ImageView img = findViewById(R.id.anim_f_logo);
        AnimationSet s = new AnimationSet(false);
        Animation zoom_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_out);
        Animation move_to_top = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move_to_top);
        move_to_top.setStartOffset(2500);


        s.addAnimation(zoom_out);
        s.addAnimation(move_to_top);
        s.setFillAfter(true);
        img.startAnimation(s);

        AnimationSet s1 = new AnimationSet(false);
        Animation fade_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        fade_in.setStartOffset(3500);
        s1.addAnimation(fade_in);
        s1.setFillAfter(true);
        RR12.startAnimation(s1);
        RR12.setAlpha(1);



        login_show_sheet = findViewById(R.id.LoginBottomSheet);
        register_show_sheet = findViewById(R.id.RegisterBottomSheet);



        login_show_sheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(IntroActivity.this);
                bottomSheetDialog.setContentView(R.layout.login_bottom_sheet);

                LoginEmail = bottomSheetDialog.findViewById(R.id.EmailFieldEntry);
                LoginPass = bottomSheetDialog.findViewById(R.id.PasswordFieldEntry);
                Login = bottomSheetDialog.findViewById(R.id.LoginButtonClick);

                bottomSheetDialog.show();


                Login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        String email = LoginEmail.getText().toString();
                        String Pass = LoginPass.getText().toString();

                        LoginUser1(email,Pass);

                    }
                });


            }
        });

        register_show_sheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(IntroActivity.this);
                bottomSheetDialog.setContentView(R.layout.register_bottom_sheet);


                RegisterName = bottomSheetDialog.findViewById(R.id.NameFieldRegister);
                RegisterEmail = bottomSheetDialog.findViewById(R.id.EmailFieldRegister);
                RegisterPhone = bottomSheetDialog.findViewById(R.id.PhoneFieldRegister);
                RegisterPass = bottomSheetDialog.findViewById(R.id.PasswordFieldRegister);
                Register = bottomSheetDialog.findViewById(R.id.RegisterButtonClick);

                bottomSheetDialog.show();

                Register.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String email,Pass,Name,Phone;
                        email = RegisterEmail.getText().toString();
                        Pass = RegisterPass.getText().toString();
                        Name = RegisterName.getText().toString();
                        Phone = RegisterPhone.getText().toString();

                        createUser(email,Pass, Name, Phone,userid);

                    }
                });

            }
        });
    }







    public void LoginUser1(String email, String Pass)
    {
        if(email.isEmpty()||Pass.isEmpty())
        {
            Toast.makeText(this, "Empty Fields", Toast.LENGTH_SHORT).show();
        }
        else
        {
            auth.signInWithEmailAndPassword(email,Pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful())
                            {
                                Toast.makeText(IntroActivity.this, "Error", Toast.LENGTH_SHORT).show();

                            }
                            else
                            {
                                finish();
                                Intent i = new Intent(IntroActivity.this, MainActivity.class);
                                startActivity(i);
                            }
                        }
                    });
        }


    }




    public void createUser(final String email,final  String Pass,final String Name,final String Phone, final int userid)
    {

            final String UserID = "User"+ String.valueOf(userid+1);


        if(email.isEmpty() || Pass.isEmpty())
        {
            Toast.makeText(this, "Fill All Fields Correctly", Toast.LENGTH_SHORT).show();

        }
        else
        {
            auth.createUserWithEmailAndPassword(email, Pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful())
                            {
                                Toast.makeText(IntroActivity.this, "Problem Arise", Toast.LENGTH_SHORT).show();

                            }
                            else
                            {
                                user = auth.getCurrentUser();
                                User User1 = new User(UserID,Name,Pass,Phone,email,0);
                                reference.child("User").child(user.getUid()).setValue(User1)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful())
                                                {
                                                    reference.child("UserID").setValue(userid+1);
                                                    Toast.makeText(IntroActivity.this, "Go back and Login", Toast.LENGTH_SHORT).show();

                                                }
                                            }
                                        });

                            }
                        }
                    });


        }
    }








}
