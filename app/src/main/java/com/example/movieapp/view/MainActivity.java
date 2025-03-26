package com.example.movieapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.movieapp.R;
import com.example.movieapp.databinding.ActivityMainBinding;
import com.example.movieapp.model.MovieModel;
import com.example.movieapp.utils.MovieClickListener;
import com.example.movieapp.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieClickListener {

    ActivityMainBinding binding;
    MovieViewModel viewModel;

    MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        movieAdapter = new MovieAdapter(getApplicationContext(), new ArrayList<>(), this);
        binding.recyclerViewMovies.setAdapter(movieAdapter);
        binding.recyclerViewMovies.setLayoutManager(new LinearLayoutManager(this));

        viewModel.getMovieData().observe(this, new Observer<ArrayList<MovieModel>>() {
            @Override
            public void onChanged(ArrayList<MovieModel> movies) {
                movieAdapter.setMovies(movies);
            }
        });

        viewModel.getNoResultsMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String message) {
                if (message != null && !message.isEmpty()) {
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();

                    Log.d("Toast", "This is a toast in main activity");
                }
            }
        });

        binding.btnSearchMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = binding.editTextMovie.getText().toString();
                viewModel.Search(query);
            }
        });

    }

    @Override
    public void onMovieClick(MovieModel movie) {
        Intent intent = new Intent(this, MovieDetailsActivity.class);

        intent.putExtra("movieTitle", movie.getTitle());
        intent.putExtra("movieYear", movie.getYear());
        intent.putExtra("moviePoster", movie.getPosterPath());
        intent.putExtra("movieRatings", movie.getRatings());
        intent.putExtra("movieDescription", movie.getDescription());
        intent.putExtra("movieDirector", movie.getDirector());

        startActivity(intent);
    }
}
