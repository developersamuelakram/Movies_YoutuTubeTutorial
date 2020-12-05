package com.example.themovies.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.themovies.Entity.MoviesDao;
import com.example.themovies.Favorite;
import com.example.themovies.Repository.Repository;
import com.example.themovies.Result;

import java.util.List;

public class MovieViewModel extends ViewModel {


    Repository repository;
    MoviesDao moviesDao;

    public MovieViewModel(Repository repository) {
        this.repository  = repository;

    }

    public MutableLiveData<List<Result>> getMoviesFromApi () {
        return repository.GetMutableLiveData();
    }

    public void addMoviesIntoDatabase(Result result) {

        repository.addingTheseMovies(result);
    }

    public LiveData<List<Result>> showMovieinDb() {

       return repository.getDatabaseMovies();
    }



    // for favoriting movies

    public void addFavMovies(Favorite favorite) {

        repository.FAVORITINGmovies(favorite);
    }

    public LiveData<List<Favorite>> showFavMovies() {

        return repository.getFavoriteMoviesidb();
    }

}
