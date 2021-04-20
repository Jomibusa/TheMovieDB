package com.jomibusa.themoviedb.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.jomibusa.themoviedb.R;
import com.jomibusa.themoviedb.adapter.MovieAdapter;
import com.jomibusa.themoviedb.modelo.Movie;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private MovieAdapter dAdapter;
    private RecyclerView dRecyclerView;
    private SearchView searchView;
    private ArrayList<Movie> arrayDatabase = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}