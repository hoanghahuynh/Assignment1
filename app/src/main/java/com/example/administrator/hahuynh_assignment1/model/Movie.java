package com.example.administrator.hahuynh_assignment1.model;

import com.example.administrator.hahuynh_assignment1.parceler.MyParceler;
import com.example.administrator.hahuynh_assignment1.utils.Constant;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 10/14/2016.
 */

public class Movie {

    @SerializedName("title")
    private String title;

    @SerializedName("overview")
    private String overview;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("backdrop_path")
    private String backdropPath;

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return Constant.STATIC_BASE_URL + posterPath;
    }

    public String getBackdropPath() {
        return Constant.STATIC_BASE_URL + backdropPath;
    }

    public Movie(MyParceler myParceler) {
        this.title = myParceler.getTitle();
        this.overview = myParceler.getOverview();
        this.posterPath = myParceler.getPosterPath();
        this.backdropPath = myParceler.getBackdropPath();
    }

}
