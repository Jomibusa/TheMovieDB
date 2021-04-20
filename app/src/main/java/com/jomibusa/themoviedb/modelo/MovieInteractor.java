package com.jomibusa.themoviedb.modelo;

import android.util.Log;

import com.jomibusa.themoviedb.Interface.TheMovieDBService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieInteractor {

    private static final String TAG = "MovieInteractor";

    public interface onDetailsFetched {
        void onSuccess(ArrayList<Result> results, int page);

        void onFailure();

    }

    public void getMovies(final onDetailsFetched listener) {

        String url = "https://api.themoviedb.org/3/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TheMovieDBService service = retrofit.create(TheMovieDBService.class);
        Call<Movie> call = service.getJson();
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (!response.isSuccessful()) {
                    listener.onFailure();
                    return;
                }
                Movie contenido = response.body();
                if (contenido != null) {
                    List<Result> listProducts = contenido.getResults();
                    int page = contenido.getTotalPages();
                    if (listProducts.size() > 0) {
                        ArrayList<Result> lista = new ArrayList<Result>(listProducts);
                        listener.onSuccess(lista, page);
                    } else {
                        listener.onFailure();
                    }

                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                listener.onFailure();
                Log.e(TAG, "onFailure" + t.getMessage());
            }
        });
    }

    public void getMoviesByPage(final onDetailsFetched listener, int numPage) {

        String url = "https://api.themoviedb.org/3/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TheMovieDBService service = retrofit.create(TheMovieDBService.class);
        Call<Movie> call = service.getJson2(String.valueOf(numPage));
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (!response.isSuccessful()) {
                    listener.onFailure();
                    return;
                }
                Movie contenido = response.body();
                if (contenido != null) {
                    List<Result> listProducts = contenido.getResults();
                    int page = contenido.getTotalPages();
                    if (listProducts.size() > 0) {
                        ArrayList<Result> lista = new ArrayList<Result>(listProducts);
                        listener.onSuccess(lista, page);
                    } else {
                        listener.onFailure();
                    }

                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                listener.onFailure();
                Log.e(TAG, "onFailure" + t.getMessage());
            }
        });
    }

}
