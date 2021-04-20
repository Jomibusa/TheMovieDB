package com.jomibusa.themoviedb.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.widget.ImageView;
import android.widget.TextView;

import com.jomibusa.themoviedb.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class MovieDetail extends AppCompatActivity {

    private String imagen, video, descripcion, nombre;
    private TextView txtDescripcion;
    private AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        txtDescripcion = findViewById(R.id.txtDescripcion);
        appBarLayout = findViewById(R.id.app_bar);

        Bundle intent = getIntent().getExtras();
        imagen = intent.getString("imagen");
        descripcion = intent.getString("descripcion");
        nombre = intent.getString("nombre");

        toolBarLayout.setTitle(nombre);
        txtDescripcion.setText(descripcion);

        String buildUrl = "https://image.tmdb.org/t/p/w500" + imagen;
        ImageView img = new ImageView(this);
        try {
            Picasso.get().load(buildUrl).into(new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    toolBarLayout.setBackground(new BitmapDrawable(getApplicationContext().getResources(), bitmap));
                }

                @Override
                public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {

                }
            });

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/results?search_query=" + nombre));
            startActivity(appIntent);
        });
    }
}