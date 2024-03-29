package com.example.egypttourguide.places;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.egypttourguide.places.alexandria.Alexandria;
import com.example.egypttourguide.places.alexandria.AlexandriaDAO;
import com.example.egypttourguide.places.aswan.Aswan;
import com.example.egypttourguide.places.aswan.AswanDAO;
import com.example.egypttourguide.places.cairo.Cairo;
import com.example.egypttourguide.places.cairo.CairoDAO;
import com.example.egypttourguide.favourite.Favourite;
import com.example.egypttourguide.favourite.FavouriteDAO;
import com.example.egypttourguide.places.luxor.Luxor;
import com.example.egypttourguide.places.luxor.LuxorDAO;
import com.example.egypttourguide.places.siwa.Siwa;
import com.example.egypttourguide.places.siwa.SiwaDAO;

@Database(entities = {Alexandria.class, Cairo.class, Siwa.class, Luxor.class, Aswan.class, Favourite.class}, version = 2)
public abstract class PlacesDatabase extends RoomDatabase {

    private static PlacesDatabase ourInstance;

    public static PlacesDatabase getInstance(Context context) {
        if (ourInstance == null) {

            ourInstance = Room.databaseBuilder (context,
                    PlacesDatabase.class, "placesDB.db")
                    .createFromAsset ("databases/placesDB.db")
                    .allowMainThreadQueries ()
                    .build ();
        }
        return ourInstance;
    }

    public abstract AlexandriaDAO alexandriaDAO();

    public abstract CairoDAO cairoDAO();

    public abstract SiwaDAO siwaDAO();

    public abstract LuxorDAO luxorDAO();

    public abstract AswanDAO aswanDAO();

    public abstract FavouriteDAO favouriteDAO();

//    public abstract PictureDAO pictureDAO();
}
