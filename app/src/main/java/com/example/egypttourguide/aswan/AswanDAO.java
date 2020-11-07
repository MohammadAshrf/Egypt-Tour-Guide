package com.example.egypttourguide.aswan;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AswanDAO {
    @Query("select * from aswan")
    List<Aswan> selectAswan();

    @Query("select * from aswan where aswanId=:title")
    Aswan selectPlaceByTitle(int title);
}
