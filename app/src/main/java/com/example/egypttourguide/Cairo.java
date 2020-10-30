package com.example.egypttourguide;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cairo")
public class Cairo {

    @PrimaryKey(autoGenerate = true)
    public int cairoId;

    public String title, description, picture;
}
