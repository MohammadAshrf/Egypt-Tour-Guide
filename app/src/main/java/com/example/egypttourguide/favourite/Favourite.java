package com.example.egypttourguide.favourite;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favourites")

public class Favourite {
    @PrimaryKey(autoGenerate = true)
    public int favId;

    public String title, picture;

}
