package com.example.eddy102.exerciser2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by eddy102 on 15/03/2018.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    // Store a member variable for the movies
    private List<MovieObject> movies;
    // Store the context for easy access
    private Context context;

    // Pass in the movie array into the constructor
    public MovieAdapter(List<MovieObject> movies, Context context) {
        this.movies = movies;
        this.context = context;
    }

    // Easy access to the context object in the recyclerView
    private Context getContext() {
        return context;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context contextTemp = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(contextTemp);

        // Inflate the custom layout
        View movieView = inflater.inflate(R.layout.movie_items, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(movieView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(MovieAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        MovieObject moviesTemp = movies.get(position);

        // Set item views based on your views and data model
        TextView tvTittle = viewHolder.tittle;
        tvTittle.setText(moviesTemp.getTittle());
        TextView tvContent = viewHolder.content;
        tvContent.setText(moviesTemp.getOverview());
        ImageView imgPoster = viewHolder.imgView;
        Picasso.with(context).load("http://image.tmdb.org/t/p/w500/"
                + moviesTemp.getPosterPath()).into(imgPoster);
        ImageView imgButtonPlay = viewHolder.btnPlay;
        if (moviesTemp.isVideo(moviesTemp.getVideo())) {
            imgButtonPlay.setVisibility(View.INVISIBLE);
        } else {
            imgButtonPlay.setBackgroundResource(R.drawable.play);
        }

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return movies.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public ImageView imgView;
        public TextView tittle, content;
        public ImageButton btnPlay;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {

            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            itemView.setOnClickListener(this);
            imgView = (ImageView) itemView.findViewById(R.id.imgView);
            tittle = (TextView) itemView.findViewById(R.id.tvTittle);
            content = (TextView) itemView.findViewById(R.id.tvContent);
            btnPlay = (ImageButton) itemView.findViewById(R.id.btnPlay);
        }

        @Override
        public void onClick(View view) {
            int position = getLayoutPosition();
            MovieObject movieDetail = movies.get(position);
            MovieDetailActivity(movieDetail.getTittle(), movieDetail.getBackDropPath(),
                    movieDetail.getOverview(), movieDetail.getVoteAvarage(),
                    movieDetail.getReleaseDate());
        }
    }

    public void MovieDetailActivity(String tittle, String imgBackDrop, String description,
                                    String voteAvarage, String releaseDay) {

        Intent intent = new Intent(context, MovieDetails.class);

        // Send data to MovieDetailActivity
        intent.putExtra("key_tittle",tittle);
        intent.putExtra("key_imgBackDrop",imgBackDrop);
        intent.putExtra("key_description",description);
        intent.putExtra("key_voteAvarage",voteAvarage);
        intent.putExtra("key_releaseDay",releaseDay);

        context.startActivity(intent);
    }
}
