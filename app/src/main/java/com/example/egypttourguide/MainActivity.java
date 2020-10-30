package com.example.egypttourguide;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    AlexandriaDAO alexandriaDAO;
    CairoDAO cairoDAO;
    SiwaDAO siwaDAO;
    LuxorDAO luxorDAO;
    AswanDAO aswanDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        ViewPager viewPager = findViewById (R.id.viewpager);
        alexandriaDAO = PlacesDatabase.getInstance (this).alexandriaDAO ();
        cairoDAO = PlacesDatabase.getInstance (this).cairoDAO ();
        siwaDAO = PlacesDatabase.getInstance (this).siwaDAO ();
        luxorDAO = PlacesDatabase.getInstance (this).luxorDAO ();
        aswanDAO = PlacesDatabase.getInstance (this).aswanDAO ();

        PlacesAdapter adapter = new PlacesAdapter (this, getSupportFragmentManager ());

        viewPager.setAdapter (adapter);

        TabLayout tabLayout = findViewById (R.id.tabs);
        tabLayout.setupWithViewPager (viewPager);
    }
}