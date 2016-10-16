package com.example.administrator.hahuynh_assignment1.adapter;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.hahuynh_assignment1.R;
import com.example.administrator.hahuynh_assignment1.model.Movie;

import java.util.List;

/**
 * Created by Administrator on 10/14/2016.
 */

public class MovieAdapter extends ArrayAdapter<Movie> {

    private List<Movie> mMovies;

    public MovieAdapter(Context context, List<Movie> movies) {
        super(context, -1);
        mMovies = movies;
    }


    @Override
    public int getCount() {
        return mMovies.size();
    }

    @Nullable
    @Override
    public Movie getItem(int position) {
        return mMovies.get(position);
    }

    //    //heterogenous
/*    @Override
    public int getItemViewType(int position) {
        int numItem = getItem(position).color.ordinal();
        return numItem;
    }*/

/*        @Override
    public int getViewTypeCount() {
        return Movie.ColorValues.values().length;
    }*/

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            //heterogenous
//            int type = getItemViewType(position);
//            convertView = getInflatedLayoutForType(position,parent);

            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_movie, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);
            viewHolder.ivCover = (ImageView) convertView.findViewById(R.id.ivCover);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Movie movie = getItem(position);

        //Fill the data
        viewHolder.tvTitle.setText(movie.getTitle());
        viewHolder.tvOverview.setText(movie.getOverview());

        Configuration configuration = getContext().getResources()
                .getConfiguration();
        if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Glide.with(getContext())
                    .load(movie.getPosterPath())
                    .placeholder(R.drawable.placeholder)
                    .into(viewHolder.ivCover);
        } else {
            Glide.with(getContext())
                    .load(movie.getBackdropPath())
                    .placeholder(R.drawable.placeholder)
                    .into(viewHolder.ivCover);
        }
        return convertView;
    }

    private class ViewHolder {
        public TextView tvTitle;
        public TextView tvOverview;
        public ImageView ivCover;
    }

    // Given the item type, responsible for returning the correct inflated XML layout file
    private View getInflatedLayoutForType(int position, ViewGroup parent) {
//        if (type == Movie.ColorValues.BLUE.ordinal()) {
        if(position % 3 == 0) {
            return LayoutInflater.from(getContext()).inflate(R.layout.item_movieb, parent, false);
//        } else if (type == Movie.ColorValues.RED.ordinal()) {
        } else if (position % 3 == 1) {
            return LayoutInflater.from(getContext()).inflate(R.layout.item_movie, parent, false);
//        } else if (type == Movie.ColorValues.GREEN.ordinal()) {
        } else if (position % 3 == 2) {
            return LayoutInflater.from(getContext()).inflate(R.layout.item_movieg, parent, false);
        } else {
            return null;
        }
    }
}
