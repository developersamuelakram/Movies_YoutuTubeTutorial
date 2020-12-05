package com.example.themovies.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.themovies.Entity.FavmovieDao;
import com.example.themovies.Entity.FavoriteMoviesDatabase;
import com.example.themovies.Entity.MoviesDao;
import com.example.themovies.Entity.MoviesDatabase;
import com.example.themovies.Favorite;
import com.example.themovies.Model.Info;
import com.example.themovies.R;
import com.example.themovies.Result;
import com.example.themovies.Service.MovieService;
import com.example.themovies.Service.RetrofitInstance;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    MutableLiveData<List<Result>> MutableLiveData = new MutableLiveData<>();
    Application application;
    ArrayList<Result> resultArrayList = new ArrayList<Result>();
    MoviesDao moviesDao;
    FavmovieDao favmovieDao;

    public Repository(Application application) {
        this.application = application;
        MoviesDatabase moviesDatabase = MoviesDatabase.getInstance(application);
        FavoriteMoviesDatabase favoriteMoviesDatabase = FavoriteMoviesDatabase.getInstance(application);
        favmovieDao = favoriteMoviesDatabase.getFavmovieDao();

        moviesDao = moviesDatabase.getMovieDao();
    }

    public MutableLiveData<List<Result>> GetMutableLiveData () {

        MovieService movieService = RetrofitInstance.getMoviesServices();
        Call<Info> call = movieService.getMoviesFromTMBDB(application.getString(R.string.api_key));

        call.enqueue(new Callback<Info>() {
            @Override
            public void onResponse(Call<Info> call, Response<Info> response) {

                Info info = response.body();

                if (info!=null && info.getResults()!=null) {

                    resultArrayList = (ArrayList<Result>) info.getResults();
                    MutableLiveData.setValue(resultArrayList);
                }
            }

            @Override
            public void onFailure(Call<Info> call, Throwable t) {

            }
        });


        return MutableLiveData;


    }

// movies locally stored
    public LiveData<List<Result>> getDatabaseMovies( ) {
     return  moviesDao.showAllMovies();

    }

    // movies that you favorited

    public LiveData<List<Favorite>> getFavoriteMoviesidb( ) {
        return  favmovieDao.getFavoriteMovies();

    }



    public void addingTheseMovies (Result result) {

        new AddMovieAsync(moviesDao).execute(result);
    }

    public static class AddMovieAsync extends AsyncTask<Result, Void, Void> {

        MoviesDao moviesDao;

        public AddMovieAsync(MoviesDao moviesDao) {
            this.moviesDao = moviesDao;
        }

        @Override
        protected Void doInBackground(Result... results) {

            moviesDao.insertMovies(results[0]);
            return null;
        }
    }



    public void FAVORITINGmovies (Favorite favorite) {

        new FavmovieAsync(favmovieDao).execute(favorite);
    }

    public static class FavmovieAsync extends AsyncTask<Favorite, Void, Void> {

        FavmovieDao favmovieDao;

        public FavmovieAsync(FavmovieDao favmovieDao) {
            this.favmovieDao = favmovieDao;
        }

        @Override
        protected Void doInBackground(Favorite... favorites) {

            favmovieDao.addFavmovie(favorites[0]);
            return null;
        }
    }
}
