package com.example.egypttourguide;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SiwaDAO {
    @Query("select * from siwa")
    List<Siwa> selectSiwa();
}
