package com.example.movieapp.viewmodel;

import android.annotation.SuppressLint;
import android.graphics.Movie;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movieapp.R;
import com.example.movieapp.model.MovieModel;
import com.example.movieapp.utils.ApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MovieViewModel extends ViewModel {

    private MutableLiveData<ArrayList<MovieModel>> movieData = new MutableLiveData<>();
    private MutableLiveData<String> noResultsMessage = new MutableLiveData<>();

    private String api_key = "https://www.omdbapi.com/?apikey=fc822bd6";

    private ArrayList<MovieModel> movies = new ArrayList<>();
    private JSONArray moviesArray;

    public LiveData<String> getNoResultsMessage() {
        return noResultsMessage;
    }


    public LiveData<ArrayList<MovieModel>> getMovieData() {
        return movieData;
    }

    public void Search(String keywords) {
        String url = api_key + "&s=" + keywords;

        ApiClient.get(url, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e("request fail", e.toString());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    try {
                        String responseData = response.body().string();
                        JSONObject json = new JSONObject(responseData);
                        moviesArray = json.getJSONArray("Search");

                        if (moviesArray.length() == 0) {
                            noResultsMessage.postValue("No movies found!");
                        } else {

                            movies.clear();

                            List<MovieModel> movieList = new ArrayList<>();

                            for (int i = 0; i < moviesArray.length(); i++) {
                                JSONObject movieJson = moviesArray.getJSONObject(i);
                                String imdbID = movieJson.getString("imdbID");
                                String url_movie = api_key + "&i=" + imdbID;

                                fetchMovieDetails(url_movie, movieList);
                            }

                            movieData.postValue(new ArrayList<>(movieList));
                        }

                    } catch (JSONException e) {
                        noResultsMessage.postValue("No movies found!");
                        Log.e("JSON Parsing Error", e.toString());
                    }
                }
            }
        });
    }

    private void fetchMovieDetails(String url_movie, List<MovieModel> movieList) {
        ApiClient.get(url_movie, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e("Request Error", e.toString());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    try {
                        String responseData = response.body().string();
                        JSONObject movieJson = new JSONObject(responseData);

                        String title = movieJson.getString("Title");
                        String year = movieJson.getString("Year");
                        String posterPath = movieJson.getString("Poster");
                        String ratings = movieJson.getString("imdbRating");
                        String description = movieJson.getString("Plot");
                        String director = movieJson.getString("Director");
                        Log.i("Movie", title + ratings + director);

                        MovieModel movieModel = new MovieModel(title, year, posterPath, ratings, description, director);
                        movieList.add(movieModel);

                        if (movieList.size() == moviesArray.length()) {
                            movieData.postValue(new ArrayList<>(movieList));
                        }

                    } catch (JSONException e) {
                        Log.e("JSON Parsing Error", e.toString());
                    }
                }
            }
        });
    }
}
