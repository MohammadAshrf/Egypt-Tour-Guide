package com.example.egypttourguide.alexandria;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AlexandriaDAO {

    @Query("select * from alexandria")
    List<Alexandria> selectAlexandria();

    @Query("select * from alexandria where alexandriaId=:title")
    Alexandria selectPlaceByTitle(int title);
}
