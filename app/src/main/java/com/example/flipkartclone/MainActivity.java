package com.example.flipkartclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {


    int backPressedOnce = 0;
    BottomNavigationView bottomNavigationView;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bottomNavigationView = findViewById(R.id.bottom_nav);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ShopFragement()).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        dl = findViewById(R.id.drawer_layout);
        t = new ActionBarDrawerToggle(this, dl,R.string.navigation_drawer_open, R.string.navigation_drawer_close);


        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        nv = findViewById(R.id.side_nav_view);



        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                switch(id)
                {
                    case R.id.FlipkartPlusZone:
                        Intent i = new Intent(MainActivity.this,PlusZoneActivity.class);
                        startActivity(i);
                        break;
                    case R.id.Electronics:
                        i = new Intent(MainActivity.this, ElectronicsActivity.class);
                        startActivity(i);
                        break;
                    case R.id.Language:
                        i = new Intent(MainActivity.this, LanguageActivity.class);
                        startActivity(i);
                        break;
                    default:
                        return true;
                }

                dl.closeDrawer(GravityCompat.START);

                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        int id = item.getItemId();
        if(id==R.id.Profile_nav_button)
        {
            Intent i = new Intent(this, ProfileActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null;

            switch (menuItem.getItemId())
            {
                case R.id.Shop:
                    selectedFragment = new ShopFragement();
                    break;

                case R.id.SuperCoin:
                    selectedFragment = new SuperCoinFragment();
                    break;
                case R.id.Video:
                    selectedFragment = new VideoFragment();
                    break;
                case R.id.Ideas:
                    selectedFragment = new IdeasFragment();
                    break;

                case R.id.Games:
                    selectedFragment = new GamesFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
    };



    @Override
    public void onBackPressed() {

        if(backPressedOnce == 0)
        {
            backPressedOnce = 1;
            Toast.makeText(this, "Press again to Exit", Toast.LENGTH_SHORT).show();
        }
        else
        {
            finish();
            System.exit(0);
        }

    }
}
