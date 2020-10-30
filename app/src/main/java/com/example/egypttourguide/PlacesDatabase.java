package com.example.egypttourguide;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Alexandria.class, Cairo.class, Siwa.class, Luxor.class, Aswan.class}, version = 1)
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

}
