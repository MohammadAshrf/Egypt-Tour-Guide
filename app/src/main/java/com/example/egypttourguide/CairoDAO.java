package com.example.egypttourguide;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CairoDAO {

    @Query("select * from cairo")
    List<Cairo> selectCairo();
}
