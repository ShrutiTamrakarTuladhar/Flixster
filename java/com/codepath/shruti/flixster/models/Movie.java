package com.codepath.shruti.flixster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Movie {

    String backdropPath;
    String posterPath;
    String title;
    String overview;
    double rating;
    int movieId;
    String releaseDate;

//For the Parcle library
    public Movie() {}


// constructor for jsonObject data - movie object
    public Movie (JSONObject jsonObject) throws JSONException {
        // adding exception to the method signature - exception should be handle on call

        backdropPath = jsonObject.getString("backdrop_path");
        posterPath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
        rating = jsonObject.getDouble("vote_average");
        movieId = jsonObject.getInt("id");
        releaseDate = jsonObject.getString("release_date");
    }

 // Takes in the jason array and returns the list of movies


    public static List<Movie> fromJsonArray(JSONArray movieJsonArray) throws JSONException {
        //movieJsonArray - argument for the function

        List<Movie> movies = new ArrayList<>();

        // each element in the array will be a movie
        for(int i = 0; i < movieJsonArray.length(); i++)
        {
            //adding element to movie list

            movies.add(new Movie(movieJsonArray.getJSONObject(i)));
        }
        return movies;

    }

    public String getPosterPath() {

        // Appending the size of the posts "w342"
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getBackdropPath() {

        //For landscape mode
        // Hardcode - Fix later
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath );
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public double getRating() {
        return rating;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}
