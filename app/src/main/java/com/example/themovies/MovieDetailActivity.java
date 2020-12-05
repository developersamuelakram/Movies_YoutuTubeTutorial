package com.example.themovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.themovies.DI.App;
import com.example.themovies.DI.MoviesViewModelFactory;
import com.example.themovies.ViewModel.MovieViewModel;
import com.example.themovies.databinding.ActivityMovieDetailBinding;

import javax.inject.Inject;

public class MovieDetailActivity extends AppCompatActivity {

    ActivityMovieDetailBinding activityMovieDetailBinding;

    Result result;

    Button button;
    Favorite favorite;

    MovieViewModel movieViewModel;

    @Inject
    MoviesViewModelFactory moviesViewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        activityMovieDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);

        // dagger injection
        App.getApp().getMovieComponent().inject(this);

        movieViewModel = ViewModelProviders.of(this, moviesViewModelFactory).get(MovieViewModel.class);
        button = (Button) findViewById(R.id.favbutton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                result =  getIntent().getParcelableExtra("movieresult");



                String posterPath = result.getPosterPath();
                Double voteAverage = result.getVoteAverage();
                String overview = result.getOverview();


                // whatever movie you clicked on and are seeing detail of
                //if you favorite it, its being favorited below

                favorite = new Favorite(overview, posterPath, voteAverage);
                movieViewModel.addFavMovies(favorite);
                Intent intent = new Intent(MovieDetailActivity.this, FavoriteActivity.class);
                intent.putExtra("favoritedmovie", favorite);
                Toast.makeText(MovieDetailActivity.this, "Favorited", Toast.LENGTH_SHORT).show();
                startActivity(intent);




            }
        });


       result =  getIntent().getParcelableExtra("movieresult");

       String moviename = result.getOriginalTitle();
        getSupportActionBar().setTitle(moviename);





        activityMovieDetailBinding.setResult(result);





    }


}