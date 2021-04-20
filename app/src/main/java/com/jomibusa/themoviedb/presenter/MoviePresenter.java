package com.jomibusa.themoviedb.presenter;

import androidx.annotation.NonNull;

import com.jomibusa.themoviedb.Interface.MovieView;
import com.jomibusa.themoviedb.base.BasePresenter;
import com.jomibusa.themoviedb.modelo.MovieInteractor;
import com.jomibusa.themoviedb.modelo.Result;

import java.util.ArrayList;

public class MoviePresenter extends BasePresenter implements MovieInteractor.onDetailsFetched{

    private MovieView view;
    private MovieInteractor itemsInteractor;

    public MoviePresenter(@NonNull MovieView view, @NonNull MovieInteractor listaInteractor) {
        this.view = view;
        this.itemsInteractor = listaInteractor;
    }

    public void cargarDatos(){
        itemsInteractor.getMovies(this);
    }

    @Override
    public void onSuccess(ArrayList<Result> results) {
        view.cargarRecylcer(results);
    }

    @Override
    public void onFailure() {
        view.mostrarError();
    }

}
