package com.example.themovies.Entity;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.themovies.Result;

import java.util.List;

@Dao
public interface MoviesDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovies (Result result);

    @Query("select * from movies")
     LiveData<List<Result>> showAllMovies();


}
