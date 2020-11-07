package com.example.egypttourguide.siwa;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "siwa")
public class Siwa {
    @PrimaryKey(autoGenerate = true)
    public int siwaId;

    public String title, description, picture;

}
