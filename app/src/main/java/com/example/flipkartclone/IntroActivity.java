package com.example.flipkartclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.animation.AnimatorSetCompat;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class IntroActivity extends AppCompatActivity {


    Button login_show_sheet,register_show_sheet;
    Button Login, Register;
    RelativeLayout RR12;

    EditText LoginEmail, LoginPass,RegisterEmail, RegisterName, RegisterPass, RegisterPhone;

    FirebaseAuth auth;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        RR12 = findViewById(R.id.RR1);
        auth = FirebaseAuth.getInstance();






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

                        createUser(email,Pass, Name, Phone);

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




    public void createUser(String email, String Pass, String Name, String Phone)
    {


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
                                Toast.makeText(IntroActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


        }
    }








}
