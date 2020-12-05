package com.example.themovies.Entity;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.themovies.Favorite;

@Database(entities = Favorite.class, version = 1)
public abstract class FavoriteMoviesDatabase extends RoomDatabase {

    public abstract FavmovieDao getFavmovieDao();

    public static FavoriteMoviesDatabase instance;

    public static synchronized  FavoriteMoviesDatabase getInstance(Context context) {

        if (instance == null) {


            instance = Room.databaseBuilder(context, FavoriteMoviesDatabase.class, "favmovieDBB").fallbackToDestructiveMigration().build();
        }

        return instance;


    }
}
