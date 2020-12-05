package com.example.themovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Configuration;
import android.os.Bundle;

import com.example.themovies.Adapters.FavoriteAdapter;
import com.example.themovies.Adapters.MovieAdapter;
import com.example.themovies.DI.App;
import com.example.themovies.DI.MoviesViewModelFactory;
import com.example.themovies.ViewModel.MovieViewModel;
import com.example.themovies.databinding.ActivityFavoriteBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class FavoriteActivity extends AppCompatActivity {

    ArrayList<Favorite> favoriteArrayList = new ArrayList<>();
    FavoriteAdapter mAdapter;
    MovieViewModel movieViewModel;
    RecyclerView recyclerView;
    ActivityFavoriteBinding activityFavoriteBinding;

    @Inject
    MoviesViewModelFactory moviesViewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Favorites");

        activityFavoriteBinding = DataBindingUtil.setContentView(this, R.layout.activity_favorite);

        // dagger injection
        App.getApp().getMovieComponent().inject(this);

        movieViewModel = ViewModelProviders.of(this, moviesViewModelFactory).get(MovieViewModel.class);

        movieViewModel.showFavMovies().observe(this, new Observer<List<Favorite>>() {
            @Override
            public void onChanged(List<Favorite> favorites) {
                favoriteArrayList = (ArrayList<Favorite>) favorites;

                showFavoriteMoviesRecyclerView(favoriteArrayList);
            }
        });




    }

    private void showFavoriteMoviesRecyclerView(ArrayList<Favorite> favoriteArrayList) {

        mAdapter = new FavoriteAdapter(this, favoriteArrayList);
        recyclerView = activityFavoriteBinding.RVFAVORITES;

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        } else {

            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));



        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();


    }
    }
