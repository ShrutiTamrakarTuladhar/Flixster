package com.codepath.shruti.flixster;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.codepath.shruti.flixster.adapters.MovieAdapter;
import com.codepath.shruti.flixster.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {

    public static final String NOW_PLAYING_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    public static final String TAG = "MainActivity";

    List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rvMovies = findViewById(R.id.rvMovies);
        movies = new ArrayList<>();


        // Create the adapter -
        final MovieAdapter movieAdapter = new MovieAdapter(this, movies);

        // Set the adapter on the recycler view
        rvMovies.setAdapter(movieAdapter);

        // Set a Layout Manage on the recycler view
        rvMovies.setLayoutManager(new LinearLayoutManager(this));



        // Creating a instance of the Asyn clinet
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(NOW_PLAYING_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {

                //log is used for debugging
                Log.d(TAG, "onSuccess");

                JSONObject jsonObject = json.jsonObject;

                try
                {
                    JSONArray results = jsonObject.getJSONArray("results");
                    Log.i(TAG, "" + results.toString());

                    // Calling the movie method from Movie.java file
                    movies.addAll(Movie.fromJsonArray(results));

                    // data changes
                    movieAdapter.notifyDataSetChanged();

                    Log.i(TAG, "Movies: " +  movies.size());

                }
                catch (JSONException e)
                {
                    Log.e(TAG, "Hit json exception", e);
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.d(TAG, "onFailure");
            }
        });




    }


}
