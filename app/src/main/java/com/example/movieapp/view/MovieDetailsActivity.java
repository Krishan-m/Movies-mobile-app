package com.example.movieapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.movieapp.R;
import com.example.movieapp.databinding.ActivityMainBinding;
import com.example.movieapp.databinding.ActivityMovieDetailsBinding;

public class MovieDetailsActivity extends AppCompatActivity {

    private ImageView moviePoster;
    private TextView movieTitle, movieYear, movieDescription, movieRatings, movieDirector;
    private ActivityMovieDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        moviePoster = binding.moviePoster;
        movieTitle = binding.movieTitle;
        movieYear = binding.movieYear;
        movieRatings = binding.movieRatings;
        movieDescription = binding.movieDescription;
        movieDirector = binding.movieDirector;


        Intent intent = getIntent();
        String title = intent.getStringExtra("movieTitle");
        String year = intent.getStringExtra("movieYear");
        String poster = intent.getStringExtra("moviePoster");
        String ratings = intent.getStringExtra("movieRatings");
        String description = intent.getStringExtra("movieDescription");
        String director = intent.getStringExtra("movieDirector");

        movieTitle.setText(title);
        movieYear.setText(year);
        movieRatings.setText(ratings);
        movieDescription.setText(description);
        movieDirector.setText(director);

        Glide.with(this)
                .load(poster)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(moviePoster);

        binding.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}