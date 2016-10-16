package com.example.administrator.hahuynh_assignment1.api;

import com.example.administrator.hahuynh_assignment1.model.NowPlaying;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Administrator on 10/14/2016.
 */

public interface MovieApi {
    @GET("now_playing")
    Call<NowPlaying> getNowPlaying();
}
