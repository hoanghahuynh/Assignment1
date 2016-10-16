package com.example.administrator.hahuynh_assignment1.parceler;

import com.example.administrator.hahuynh_assignment1.model.Movie;

import org.parceler.Parcel;

/**
 * Created by HoangHa on 10/15/2016.
 */
@Parcel
public class MyParceler {

    public String title;

    public String overview;

    public String posterPath;

    public String backdropPath;

    public MyParceler() {}

    public MyParceler(Movie movie){
        this.title = movie.getTitle();
        this.overview = movie.getOverview();
        this.posterPath = movie.getPosterPath();
        this.backdropPath = movie.getBackdropPath();
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }
}
