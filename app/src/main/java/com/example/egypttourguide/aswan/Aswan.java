package com.example.egypttourguide.aswan;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "aswan")
public class Aswan {
    @PrimaryKey(autoGenerate = true)
    public int aswanId;

    public String title, description, picture;
}

