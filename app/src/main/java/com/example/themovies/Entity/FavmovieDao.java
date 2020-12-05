package com.example.themovies.Entity;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.themovies.Favorite;

import java.util.List;

@Dao
public interface FavmovieDao {


    @Insert
    void addFavmovie(Favorite favorite);

    @Query("select * from favoritesmovies")
    LiveData<List<Favorite>> getFavoriteMovies();
}
