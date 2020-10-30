package com.example.egypttourguide;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AswanDAO {
    @Query("select * from aswan")
    List<Aswan> selectAswan();
}
