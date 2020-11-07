package com.example.egypttourguide.siwa;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.egypttourguide.Favourite;
import com.example.egypttourguide.FavouriteActivity;
import com.example.egypttourguide.FavouriteDAO;
import com.example.egypttourguide.ImageViewerActivity;
import com.example.egypttourguide.PlacesDatabase;
import com.example.egypttourguide.R;
import com.squareup.picasso.Picasso;

public class SiwaDetailActivity extends AppCompatActivity implements LocationListener {

    TextView tvPlaceTitle, tvPlacePlot;
    ImageView ivPic, ivStar;
    SiwaDAO siwaDAO;
    LocationManager manager;
    boolean starOn;
    Siwa siwa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_detail_places);
        tvPlaceTitle = findViewById (R.id.tvPlaceTitle);
        tvPlacePlot = findViewById (R.id.tvPlacePlot);
        ivPic = findViewById (R.id.ivPic);
        ivStar = findViewById (R.id.ivStar);

        int title = getIntent ().getIntExtra ("titles", 1);
        siwaDAO = PlacesDatabase.getInstance (this).siwaDAO ();
        siwa = siwaDAO.selectPlaceByTitle (title + 1);
        tvPlaceTitle.setText (siwa.title);
        tvPlacePlot.setMovementMethod (new ScrollingMovementMethod ());
        tvPlacePlot.setText (siwa.description);
        /**
         * Open Image on FullScreen
         */
        ivPic.setOnClickListener (v -> {
            Intent in = new Intent (SiwaDetailActivity.this, ImageViewerActivity.class);
            in.putExtra ("url", (siwa.picture));
            startActivity (in);
        });

        Picasso.get ().load (siwa.picture).placeholder (R.drawable.placeholder).into (ivPic);

        int count = PlacesDatabase.getInstance (this).favouriteDAO ().countByName (tvPlaceTitle.getText ().toString ());
        if (count == 0) {
            starOn = false;
            ivStar.setImageResource (R.drawable.ic_unstar);
        } else {
            starOn = true;
            ivStar.setImageResource (R.drawable.ic_star);
        }


    }

    public void location(View view) {
        //step1
        manager = (LocationManager) getSystemService (LOCATION_SERVICE);
        if (manager.isProviderEnabled (LocationManager.NETWORK_PROVIDER)) {
            //step2
            if (ActivityCompat.checkSelfPermission (this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission (this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                String[] perm = {Manifest.permission.ACCESS_FINE_LOCATION};
                ActivityCompat.requestPermissions (this, perm, 1);

            } else {
                manager.requestSingleUpdate (LocationManager.NETWORK_PROVIDER, this, null);
            }
        } else {
            Toast.makeText (this, "Location is off!", Toast.LENGTH_SHORT).show ();
        }

    }

    /**
     * The result of the location request
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult (requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                try {
                    manager.requestSingleUpdate (LocationManager.NETWORK_PROVIDER, this, null);
                } catch (SecurityException e) {
                    e.printStackTrace ();
                }
            } else {
                Toast.makeText (this, "Location is disabled!", Toast.LENGTH_SHORT).show ();
            }
        }
    }


    @Override
    public void onLocationChanged(Location location) {
        Intent intent = new Intent (Intent.ACTION_VIEW, Uri.parse ("google.navigation:q= " +
                tvPlaceTitle.getText ().toString () + " ,Egypt"));
        startActivity (intent);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public void star(View view) {
        /**
         *  insert*/
        if (starOn) {
            ivStar.setImageResource (R.drawable.ic_unstar);

            String title = tvPlaceTitle.getText ().toString ();
            FavouriteDAO favouriteDAO = PlacesDatabase.getInstance (this).favouriteDAO ();
            int delete = favouriteDAO.deleteByName (title);
            Toast.makeText (this, tvPlaceTitle.getText ().toString () + " removed from Favourites!", Toast.LENGTH_SHORT).show ();

        } else {
            ivStar.setImageResource (R.drawable.ic_star);

            String title = tvPlaceTitle.getText ().toString ();
            FavouriteDAO favouriteDAO = PlacesDatabase.getInstance (this).favouriteDAO ();
            int x = favouriteDAO.deleteByName (title);


            Favourite favourite = new Favourite ();
            favourite.title = tvPlaceTitle.getText ().toString ();
            favourite.picture = siwa.picture;

//            Picasso.get ().load (favourite.picture).placeholder (R.drawable.placeholder).into (ivPicture);

            long insert = PlacesDatabase.getInstance (this).favouriteDAO ().insert (favourite);
            if (insert > 0) {

                Toast.makeText (this, tvPlaceTitle.getText ().toString () + " added to Favourites!", Toast.LENGTH_SHORT).show ();
            }

        }
        starOn = !starOn;
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
        Intent in = new Intent (SiwaDetailActivity.this, FavouriteActivity.class);
        startActivity (in);
    }

}