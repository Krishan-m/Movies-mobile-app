package com.example.movieapp.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movieapp.R;
import com.example.movieapp.model.MovieModel;
import com.example.movieapp.utils.MovieClickListener;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {
    List<MovieModel> movies;
    Context context;
    MovieClickListener movieClickListener;

    public MovieAdapter(Context context, List<MovieModel> movies, MovieClickListener movieClickListener) {
        this.context = context;
        this.movies = movies;
        this.movieClickListener = movieClickListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View movieView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_box, parent, false);
        return new MovieViewHolder(movieView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        MovieModel movie = movies.get(position);

        if (context != null) {

            Glide.with(context)
                    .load(movie.getPosterPath())
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(holder.image);

        }
        holder.title.setText(movie.getTitle());
        holder.year.setText(movie.getYear());
        holder.director.setText(movie.getDirector());

        holder.itemView.setOnClickListener(v -> {
            if (movieClickListener != null){
                movieClickListener.onMovieClick(movie);
            }
        });

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setMovies(ArrayList<MovieModel> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }
}
