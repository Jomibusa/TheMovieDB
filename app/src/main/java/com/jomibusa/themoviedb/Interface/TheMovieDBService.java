package com.jomibusa.themoviedb.Interface;

import com.jomibusa.themoviedb.modelo.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TheMovieDBService {
    String API_ROUTE = "movie/popular?api_key=762b9efc4b62d1f246b562cd71740148";
    String API_ROUTE2 = "movie/popular?api_key=762b9efc4b62d1f246b562cd71740148&page=";

    @GET(API_ROUTE)
    Call<Movie> getJson();

    @GET(API_ROUTE2)
    Call<Movie> getJson2(@Query("page") String page);
}
