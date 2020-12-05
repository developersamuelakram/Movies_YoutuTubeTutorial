package com.example.themovies.DI;

import android.app.Application;



public class App extends Application {


    private static App app;
    MovieComponent movieComponent;

    public static App getApp() {
        return app;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        app = this;

        movieComponent = DaggerMovieComponent.builder().applicationModule(new ApplicationModule(this)).build();


    }


    public MovieComponent getMovieComponent() {
        return movieComponent;
    }
}
