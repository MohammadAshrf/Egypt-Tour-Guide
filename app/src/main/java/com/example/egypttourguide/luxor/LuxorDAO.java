package com.example.egypttourguide.luxor;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LuxorDAO {
    @Query("select * from luxor")
    List<Luxor> selectLuxor();

    @Query("select * from luxor where luxorId=:title")
    Luxor selectPlaceByTitle(int title);
}
