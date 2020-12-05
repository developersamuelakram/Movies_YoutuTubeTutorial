package com.example.themovies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.themovies.Adapters.MovieAdapter;
import com.example.themovies.DI.App;
import com.example.themovies.DI.MoviesViewModelFactory;
import com.example.themovies.ViewModel.MovieViewModel;
import com.example.themovies.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    ArrayList<Result> resultArrayList  = new ArrayList<>();
    ArrayList<Result> offlinelist  = new ArrayList<>();
    MovieViewModel movieViewModel;
    ActivityMainBinding activityMainBinding;
    RecyclerView recyclerView;
    MovieAdapter mAdapter;

    @Inject
    MoviesViewModelFactory moviesViewModelFactory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // dagger injection
        App.getApp().getMovieComponent().inject(this);

        movieViewModel = ViewModelProviders.of(this, moviesViewModelFactory).get(MovieViewModel.class);


        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo!=null) {

            showPopularMovies();

        } else {

            showOfflineMovies();
        }


        showPopularMovies();

    }

    private void showOfflineMovies() {

        movieViewModel.showMovieinDb().observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> results) {

                offlinelist = (ArrayList<Result>) results;

                showofflinerecyclerview(offlinelist);



            }
        });
    }

    private void showofflinerecyclerview(ArrayList<Result> offlinelist) {

        mAdapter = new MovieAdapter(this, offlinelist);
        recyclerView = activityMainBinding.RVMOVIES;

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        } else {

            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));



        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();



    }

    private void showPopularMovies() {

        //fetching movies from api
        movieViewModel.getMoviesFromApi().observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> results) {

                resultArrayList = (ArrayList<Result>) results;

                for (Result rs: resultArrayList) {

                    // saving those movies into the database.
                    movieViewModel.addMoviesIntoDatabase(rs);

                    Log.i("TAG", "movie name: " + rs.getOriginalTitle());
                }

                showRecyclerView(resultArrayList);
            }
        });


    }

    private void showRecyclerView(ArrayList<Result> resultArrayList) {

        mAdapter = new MovieAdapter(this, resultArrayList);
        recyclerView = activityMainBinding.RVMOVIES;

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        } else {

            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));



        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.Favoritesmenu) {

            startActivity(new Intent(MainActivity.this, FavoriteActivity.class));



        }
        return super.onOptionsItemSelected(item);
    }
}