package com.example.themovies.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.themovies.Favorite;
import com.example.themovies.R;
import com.example.themovies.databinding.ActivityFavoriteBinding;
import com.example.themovies.databinding.FavstylealistBinding;

import java.util.ArrayList;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.MyViewHolder> {

    Context context;
    ArrayList<Favorite> favoriteArrayList;
    FavstylealistBinding favstylealistBinding;

    public FavoriteAdapter(Context context, ArrayList<Favorite> favoriteArrayList) {
        this.context = context;
        this.favoriteArrayList = favoriteArrayList;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        favstylealistBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.favstylealist, parent,
                false);
        return new MyViewHolder(favstylealistBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Favorite favorite = favoriteArrayList.get(position);
        favstylealistBinding.setFavorite(favorite);



    }

    @Override
    public int getItemCount() {
        return favoriteArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        FavstylealistBinding favstylealistBinding;


        public MyViewHolder( FavstylealistBinding favstylealistBinding) {
            super(favstylealistBinding.getRoot());
            this.favstylealistBinding = favstylealistBinding;
        }
    }
}
