package com.example.egypttourguide.cairo;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CairoDAO {

    @Query("select * from cairo")
    List<Cairo> selectCairo();

    @Query("select * from cairo where cairoId=:title")
    Cairo selectPlaceByTitle(int title);
}
