package com.example.egypttourguide;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AlexandriaDAO {

    @Query("select * from alexandria")
    List<Alexandria> selectAlexandria();
}
