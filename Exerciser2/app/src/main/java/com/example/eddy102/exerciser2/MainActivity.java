package com.example.eddy102.exerciser2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<MovieObject> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycleView);

        //Transform from json to POJOs
        try {
            JSONObject jsonObject = new JSONObject(MyApp.msgMovie);
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            movies = new ArrayList<>();
            Gson gson = new Gson();
            for (int i = 0; i<jsonArray.length();i++){
                JSONObject finalObject = jsonArray.getJSONObject(i);
                MovieObject movie = gson.fromJson(finalObject.toString(),MovieObject.class);
                movies.add(movie);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Create adapter to recycleView
        MovieAdapter movieAdapter = new MovieAdapter(movies,this);
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
