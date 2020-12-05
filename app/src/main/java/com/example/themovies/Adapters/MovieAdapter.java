package com.example.themovies.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.themovies.MovieDetailActivity;
import com.example.themovies.R;
import com.example.themovies.Result;
import com.example.themovies.databinding.MovieliststyleBinding;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyHolder> {


    MovieliststyleBinding movieliststyleBinding;
    Context context;
    ArrayList<Result> resultArrayList;

    public MovieAdapter(Context context, ArrayList<Result> resultArrayList) {
        this.context = context;
        this.resultArrayList = resultArrayList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        movieliststyleBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.movieliststyle, parent, false);

        return new MyHolder(movieliststyleBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        Result result = resultArrayList.get(position);

        movieliststyleBinding.setResult(result);

    }

    @Override
    public int getItemCount() {
        return resultArrayList.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {

        MovieliststyleBinding movieliststyleBinding;

        public MyHolder(MovieliststyleBinding movieliststyleBinding) {
            super(movieliststyleBinding.getRoot());
            this.movieliststyleBinding = movieliststyleBinding;

            movieliststyleBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if (position != RecyclerView.NO_POSITION) {

                        Result result = resultArrayList.get(position);

                        //passing the whole result info for whatever the item is clicked.

                        Intent intent = new Intent(context, MovieDetailActivity.class);
                        intent.putExtra("movieresult", result);
                        context.startActivity(intent);

                    }

                }
            });

        }
    }
}
