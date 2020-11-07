package com.example.egypttourguide.luxor;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "luxor")
public class Luxor {
    @PrimaryKey(autoGenerate = true)
    public int luxorId;

    public String title, description, picture;
}
