package com.example.egypttourguide;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.egypttourguide.alexandria.AlexandriaDAO;
import com.example.egypttourguide.aswan.AswanDAO;
import com.example.egypttourguide.cairo.CairoDAO;
import com.example.egypttourguide.luxor.LuxorDAO;
import com.example.egypttourguide.siwa.SiwaDAO;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater ().inflate (R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId ();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected (item);
    }

    public void Favourites(MenuItem item) {
        Intent in = new Intent (MainActivity.this, FavouriteActivity.class);
        startActivity (in);
    }
}