package com.example.egypttourguide.siwa;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SiwaDAO {
    @Query("select * from siwa")
    List<Siwa> selectSiwa();

    @Query("select * from siwa where siwaId=:title")
    Siwa selectPlaceByTitle(int title);
}
