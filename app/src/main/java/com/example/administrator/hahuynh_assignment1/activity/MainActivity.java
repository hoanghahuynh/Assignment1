package com.example.administrator.hahuynh_assignment1.activity;

import android.os.Bundle;
//import android.os.Parcelable;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.administrator.hahuynh_assignment1.R;
import com.example.administrator.hahuynh_assignment1.adapter.MovieAdapter;
import com.example.administrator.hahuynh_assignment1.api.MovieApi;
import com.example.administrator.hahuynh_assignment1.model.Movie;
import com.example.administrator.hahuynh_assignment1.model.NowPlaying;
import com.example.administrator.hahuynh_assignment1.parceler.MyParceler;
import com.example.administrator.hahuynh_assignment1.utils.RetrofitUtils;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ListView lvMovie;
    private ProgressBar pbLoading;
    MovieApi mMovieApi;
    List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        pbLoading = (ProgressBar) findViewById(R.id.pdLoading);
        mMovieApi = RetrofitUtils.get(getString(R.string.api_key)).create(MovieApi.class);
        lvMovie = (ListView) findViewById(R.id.lvMovie);
        if (savedInstanceState == null) {
            fetchMovie();
        } else {
            MyParceler rsMovie;
            ArrayList<Movie> lsMovie = new ArrayList<>();
            int size = savedInstanceState.getInt("size");
            Parcelable plMovie;
            for (int i = 0; i < size; i++) {
                plMovie = savedInstanceState.getParcelable("data" + i);
                rsMovie = Parcels.unwrap(plMovie);
                lsMovie.add(new Movie(rsMovie));
            }
            movies = lsMovie;
            handleResponse();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        Parcelable plMovie;
        for (int i = 0; i < movies.size(); i++) {
            plMovie = Parcels.wrap(new MyParceler(movies.get(i)));
            savedInstanceState.putParcelable("data" + i, plMovie);
        }
        savedInstanceState.putInt("size", movies.size());
        super.onSaveInstanceState(savedInstanceState);

    }

//// Restore data: put them in either onRestoreInstancestate or OnCreate.
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        MyParceler rsMovie;
//        ArrayList<Movie> lsMovie = new ArrayList<>();
//        int size = savedInstanceState.getInt("size");
//        Parcelable plMovie;
//        for (int i = 0; i < size; i++) {
//            plMovie = savedInstanceState.getParcelable("data" + i);
//            rsMovie = Parcels.unwrap(plMovie);
//            lsMovie.add(new Movie(rsMovie));
//        }
//        movies = lsMovie;
//        handleResponse();
//    }

    private void fetchMovie() {
        mMovieApi.getNowPlaying().enqueue(new Callback<NowPlaying>() {
            @Override
            public void onResponse(Call<NowPlaying> call, Response<NowPlaying> response) {
                //Log.d("Response", String.valueOf(response.isSuccessful()));
                movies = response.body().getMovies();
                handleResponse();
            }

            @Override
            public void onFailure(Call<NowPlaying> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
    }

    private void handleResponse() {
        lvMovie.setAdapter(new MovieAdapter(MainActivity.this, movies));
        pbLoading.setVisibility(View.GONE);
    }
}
