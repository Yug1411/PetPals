package com.example.petpals;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public HomeFragment homeFragment = new HomeFragment();
    public MapFragment mapFragment = new MapFragment();
    public DocFragment docFragment = new DocFragment();
    public ProfileFragment profileFragment = new ProfileFragment();
    public SettingsFragment settingsFragment = new SettingsFragment();

    public FragmentManager fragmentManager = getSupportFragmentManager();
    public DatabaseManager databaseManager = new DatabaseManager(MainActivity.this);

    // check if current user exists


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set the default fragment as 'home'
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainFrameLayout, homeFragment);
        fragmentTransaction.commit();

        // add listeners to the navigation buttons
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                int itemId = item.getItemId();

                if (itemId == R.id.btnMap) {
                    changeHeader("PARKS");
                    fragmentTransaction.replace(R.id.mainFrameLayout, mapFragment);
                } else if (itemId == R.id.btnDoc) {
                    changeHeader("VETERINARIES");
                    fragmentTransaction.replace(R.id.mainFrameLayout, docFragment);
                } else if (itemId == R.id.btnProfile) {
                    changeHeader("MY PETS");
                    fragmentTransaction.replace(R.id.mainFrameLayout, profileFragment);
                } else if (itemId == R.id.btnSettings) {
                    changeHeader("SETTINGS");
                    fragmentTransaction.replace(R.id.mainFrameLayout, settingsFragment);
                } else {
                    changeHeader("PETPALS");
                    fragmentTransaction.replace(R.id.mainFrameLayout, homeFragment);
                }

                fragmentTransaction.commit();

                return true;
            }
        });
    }

    public void changeHeader(String text) {
        TextView textViewHeader = findViewById(R.id.textViewHeader);
        textViewHeader.setText(text);
    }
}