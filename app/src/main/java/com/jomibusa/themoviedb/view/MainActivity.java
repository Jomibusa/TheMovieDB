package com.jomibusa.themoviedb.view;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jomibusa.themoviedb.Interface.MovieView;
import com.jomibusa.themoviedb.R;
import com.jomibusa.themoviedb.adapter.MovieAdapter;
import com.jomibusa.themoviedb.base.BaseActivity;
import com.jomibusa.themoviedb.modelo.MovieInteractor;
import com.jomibusa.themoviedb.modelo.Result;
import com.jomibusa.themoviedb.presenter.MoviePresenter;

import java.util.ArrayList;

public class MainActivity extends BaseActivity<MoviePresenter> implements MovieView, SearchView.OnQueryTextListener {

    private MovieAdapter dAdapter;
    private RecyclerView dRecyclerView;
    private SearchView searchView;
    ProgressBar progressBar;
    Dialog dialog;
    private Button btnBack, btnNext;
    private TextView result;
    int paginacion = 1;

    @NonNull
    @Override
    protected MoviePresenter createPresenter(@NonNull Context context) {
        return new MoviePresenter(this, new MovieInteractor());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        getMovies();
        initListener();

        btnNext.setOnClickListener(v -> {
            paginacion++;
            progressBar.setVisibility(View.VISIBLE);
            dRecyclerView.setVisibility(View.GONE);
            btnNext.setVisibility(View.GONE);
            btnBack.setVisibility(View.GONE);
            if (paginacion > 1) {
                btnBack.setEnabled(true);
            } else if (paginacion <= 500) {
                btnNext.setEnabled(false);
            }
            getMoviesByPage();
        });

        btnBack.setOnClickListener(v -> {
            paginacion--;
            progressBar.setVisibility(View.VISIBLE);
            dRecyclerView.setVisibility(View.GONE);
            btnNext.setVisibility(View.GONE);
            btnBack.setVisibility(View.GONE);
            if (paginacion <= 1) {
                btnBack.setEnabled(false);
            }
            getMoviesByPage();
        });

    }

    public void initData() {
        dRecyclerView = findViewById(R.id.listMovies);
        searchView = findViewById(R.id.search_view_movies);
        progressBar = findViewById(R.id.progressBar);
        btnBack = findViewById(R.id.btnAtras);
        btnNext = findViewById(R.id.btnSig);
        result = findViewById(R.id.txtResults);
        dialog = new Dialog(this);
    }

    private void initListener() {
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if (dAdapter.getItemCount() <= 0) {
            dRecyclerView.setVisibility(View.GONE);
            result.setVisibility(View.VISIBLE);
        } else {
            dRecyclerView.setVisibility(View.VISIBLE);
            result.setVisibility(View.GONE);
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        dAdapter.filter(newText);
        if (dAdapter.getItemCount() <= 0) {
            dRecyclerView.setVisibility(View.GONE);
            result.setVisibility(View.VISIBLE);
        } else {
            dRecyclerView.setVisibility(View.VISIBLE);
            result.setVisibility(View.GONE);
        }
        return false;
    }

    @Override
    public void getMovies() {
        mPresenter.cargarDatos();
    }

    @Override
    public void getMoviesByPage() {
        mPresenter.cargarDatosByPage(paginacion);
    }


    @Override
    public void cargarRecylcer(ArrayList<Result> results, int page) {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        dRecyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        dRecyclerView.addItemDecoration(dividerItemDecoration);
        dAdapter = new MovieAdapter(results, R.layout.list_movie, this);
        dRecyclerView.setAdapter(dAdapter);
        progressBar.setVisibility(View.GONE);
        dRecyclerView.setVisibility(View.VISIBLE);
        btnNext.setVisibility(View.VISIBLE);
        btnBack.setVisibility(View.VISIBLE);
    }

    @Override
    public void mostrarError() {

        progressBar.setVisibility(View.GONE);

        Button btnRegresar;

        dialog.setContentView(R.layout.no_results);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        btnRegresar = dialog.findViewById(R.id.btnRegresar);

        btnRegresar.setOnClickListener(v -> {
            dialog.dismiss();
            finish();
            startActivity(new Intent(MainActivity.this, MainActivity.class));
        });

        dialog.show();

    }

}