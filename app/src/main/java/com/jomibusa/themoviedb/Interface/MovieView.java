package com.jomibusa.themoviedb.Interface;

import com.jomibusa.themoviedb.modelo.Result;

import java.util.ArrayList;

public interface MovieView {

    void getMovies();

    void getMoviesByPage();

    void cargarRecylcer(ArrayList<Result> results, int page);

    void mostrarError();

}
