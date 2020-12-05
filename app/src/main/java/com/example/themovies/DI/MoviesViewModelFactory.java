package com.example.themovies.DI;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.themovies.Repository.Repository;
import com.example.themovies.ViewModel.MovieViewModel;

import javax.inject.Inject;

public class MoviesViewModelFactory implements ViewModelProvider.Factory {



    Repository repository;

    @Inject
    public MoviesViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MovieViewModel(repository);
    }
}
