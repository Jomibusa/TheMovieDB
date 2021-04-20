package com.jomibusa.themoviedb.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.jomibusa.themoviedb.Interface.MovieView;
import com.jomibusa.themoviedb.R;
import com.jomibusa.themoviedb.adapter.MovieAdapter;
import com.jomibusa.themoviedb.base.BaseActivity;
import com.jomibusa.themoviedb.modelo.Movie;
import com.jomibusa.themoviedb.modelo.MovieInteractor;
import com.jomibusa.themoviedb.modelo.Result;
import com.jomibusa.themoviedb.presenter.MoviePresenter;

import java.util.ArrayList;

public class MainActivity extends BaseActivity<MoviePresenter> implements MovieView, SearchView.OnQueryTextListener{

    private MovieAdapter dAdapter;
    private RecyclerView dRecyclerView;
    private SearchView searchView;
    ProgressBar progressBar;

    @NonNull
    @Override
    protected MoviePresenter createPresenter(@NonNull Context context) {
        return new MoviePresenter(this, new MovieInteractor());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dRecyclerView = findViewById(R.id.listMovies);
        searchView = findViewById(R.id.search_view_movies);
        progressBar = findViewById(R.id.progressBar);
        getMovies();
        initListener();
    }

    private void initListener() {
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        dAdapter.filter(newText);
        return false;
    }

    @Override
    public void getMovies() {
        mPresenter.cargarDatos();
    }

    @Override
    public void cargarRecylcer(ArrayList<Result> results) {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        dRecyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        dRecyclerView.addItemDecoration(dividerItemDecoration);
        dAdapter = new MovieAdapter(results, R.layout.list_movie, this);
        dRecyclerView.setAdapter(dAdapter);
        progressBar.setVisibility(View.GONE);
        dRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void mostrarError() {

    }

    @Override
    public void noHayResultados() {

    }
}