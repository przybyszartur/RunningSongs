package com.example.runningsongs;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private boolean allowExit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_Option1:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Option1Fragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_Option2:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Option2Fragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_Option3:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Option3Fragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_Option4:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Option4Fragment()).addToBackStack(null).commit();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
/*
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    */

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            // handling fragment backbutton navigation
            getSupportFragmentManager().popBackStack();
        } else {
            if (allowExit) {

                super.onBackPressed();
            } else {
                Toast.makeText(this, getString(R.string.app_exit_message), Toast.LENGTH_SHORT).show();
                allowExit = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        allowExit = false;
                    }
                }, 3000);
            }
        }
    }
}