package com.jomibusa.themoviedb.Interface;

import com.jomibusa.themoviedb.modelo.Movie;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TheMovieDBService {
    String API_ROUTE = "movie/popular?api_key=762b9efc4b62d1f246b562cd71740148";

    @GET(API_ROUTE)
    Call<Movie> getJson();
}
