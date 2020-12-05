package com.example.themovies.Service;

import com.example.themovies.Model.Info;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {


    @GET("movie/popular")
    Call<Info> getMoviesFromTMBDB(@Query("api_key") String api_key);

}
