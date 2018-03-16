package com.example.eddy102.exerciser2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        //Connect to xml-file
        ImageView imgBackDrop = (ImageView) findViewById(R.id.imgBackView);
        TextView tvTittle = (TextView) findViewById(R.id.tvTittleDetail);
        TextView tvReleaseDay = (TextView) findViewById(R.id.tvReleaseDay);
        RatingBar rtBar = (RatingBar) findViewById(R.id.rtBar);
        TextView tvOverview = (TextView) findViewById(R.id.tvDescription);

        Intent intent = this.getIntent();
        tvTittle.setText(intent.getExtras().getString("key_tittle"));
        Picasso.with(this).load("http://image.tmdb.org/t/p/w500/"
                + intent.getExtras().getString("key_imgBackDrop")).into(imgBackDrop);
        tvReleaseDay.setText("Release Day: " + intent.getExtras().getString("key_releaseDay"));
        tvOverview.setText(intent.getExtras().getString("key_description"));
        tvOverview.setMovementMethod(new ScrollingMovementMethod());
        float score = Float.parseFloat(intent.getExtras().getString("key_voteAvarage"));
        rtBar.setRating(score/2);
    }
}
