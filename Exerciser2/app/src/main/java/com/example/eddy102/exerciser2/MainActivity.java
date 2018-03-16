package com.example.eddy102.exerciser2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<MovieObject> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycleView);

        //Transform from json to POJOs
        Gson gson = new Gson();
        Type listType = new TypeToken<List<MovieObject>>(){}.getType();
        List<MovieObject> movie = (List<MovieObject>) gson.fromJson(MyApp.msgMovie, listType);

        //Create adapter to recycleView
        MovieAdapter movieAdapter = new MovieAdapter(movie,this);
        recyclerView.setAdapter(movieAdapter);

        //Set LayoutManager for View
        LinearLayoutManager linearLayoutManager = new
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        //Custom Design View in Layout Manager
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }
}
