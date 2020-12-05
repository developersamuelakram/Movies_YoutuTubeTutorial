package com.example.themovies.Entity;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.themovies.Result;

@Database(entities = Result.class, version = 1)
public abstract class MoviesDatabase extends RoomDatabase {

    public abstract MoviesDao getMovieDao();

    public static MoviesDatabase instance;

    public static synchronized MoviesDatabase getInstance(Context context) {

        if (instance == null) {


            instance = Room.databaseBuilder(context, MoviesDatabase.class, "movieDBD")
                    .fallbackToDestructiveMigration().build();
        }

        return instance;
    }
}
