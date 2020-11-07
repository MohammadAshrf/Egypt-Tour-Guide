package com.example.egypttourguide;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavouriteDAO {

    @Insert
    long insert(Favourite favourite);

    @Query("SELECT * FROM favourites")
    List<Favourite> selectAll();

    @Query("SELECT count (*) from favourites where title=:title")
    int countByName(String title);


    @Query("delete from favourites where title=:title")
    int deleteByName(String title);
}

