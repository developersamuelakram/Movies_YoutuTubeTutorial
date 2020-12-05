package com.example.themovies.DI;

import android.app.Application;

import com.example.themovies.Repository.Repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {


    @Provides
    @Singleton
    Repository providesRepositry(Application application) {
        return new Repository(application);
    }
}
