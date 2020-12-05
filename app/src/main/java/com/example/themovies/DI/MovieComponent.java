package com.example.themovies.DI;


import com.example.themovies.FavoriteActivity;
import com.example.themovies.MainActivity;
import com.example.themovies.MovieDetailActivity;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {ApplicationModule.class, RepositoryModule.class})
@Singleton
public interface MovieComponent {


    void inject(MainActivity mainActivity);

    void inject(MovieDetailActivity movieDetailActivity);

    void inject(FavoriteActivity favoriteActivity);
}
