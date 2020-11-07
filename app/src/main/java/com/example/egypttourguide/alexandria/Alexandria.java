package com.example.egypttourguide.alexandria;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "alexandria")
public class Alexandria {

    @PrimaryKey(autoGenerate = true)
    public int alexandriaId;

    public String title, description, picture;
}
