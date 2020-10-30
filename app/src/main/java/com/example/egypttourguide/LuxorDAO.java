package com.example.egypttourguide;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LuxorDAO {
    @Query("select * from luxor")
    List<Luxor> selectLuxor();
}
