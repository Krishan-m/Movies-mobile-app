package com.example.movieapp.model;

import android.graphics.Movie;

public class MovieModel {
    private String title;
    private String year;
    private String posterPath;
    private String ratings;
    private String description;
    private String director;

    public MovieModel(){}

    public MovieModel(String title, String year, String posterPath, String ratings, String description, String director){
        this.title = title;
        this.year = year;
        this.posterPath = posterPath;
        this.ratings = ratings;
        this.description = description;
        this.director = director;
    }

    public String getTitle(){return title;}
    public void setTitle(String title){this.title = title;}

    public String getYear(){return year;}
    public void setYear(String year){this.year = year;}

    public String getPosterPath(){return posterPath;}
    public void setPosterPath(String posterPath){this.posterPath = posterPath;}

    public String getRatings(){return ratings;}
    public void setRatings(String ratings){this.ratings = ratings;}

    public String getDescription(){return description;}
    public void setDescription(String description){this.description = description;}

    public String getDirector(){return director;}
    public void setDirector(String director){this.director = director;}
}
