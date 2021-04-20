package com.jomibusa.themoviedb.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jomibusa.themoviedb.R;
import com.jomibusa.themoviedb.modelo.Movie;
import com.jomibusa.themoviedb.modelo.Result;
import com.jomibusa.themoviedb.view.MovieDetail;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    private int resource;
    private Context context;
    private ArrayList<Result> databaseList;
    private List<Result> databaseListFull;
    private int lastPosition = -1;

    public MovieAdapter(ArrayList<Result> databaseList, int resource, Context context) {
        this.databaseList = databaseList;
        this.context = context;
        this.resource = resource;
        this.databaseListFull = new ArrayList<>();
        databaseListFull.addAll(databaseList);
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new MovieAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieAdapter.ViewHolder holder, int position) {

        final Result database = databaseList.get(position);
        String buildUrl = "https://image.tmdb.org/t/p/w500" + database.getPosterPath();
        try {
            Picasso.get().load(buildUrl).fit().into(holder.imageView);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        holder.view.setOnClickListener(view -> {
            Intent intent = new Intent(context, MovieDetail.class);
            intent.putExtra("descripcion", database.getOverview());
            intent.putExtra("imagen", database.getBackdropPath());
            intent.putExtra("video", database.getVideo());
            intent.putExtra("nombre", database.getTitle());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return databaseList.size();
    }

    public void filter(final String search) {
        if (search.length() == 0) {
            databaseList.clear();
            databaseList.addAll(databaseListFull);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                databaseList.clear();
                List<Result> collect = databaseListFull.stream()
                        .filter(i -> i.getTitle().toLowerCase().contains(search))
                        .collect(Collectors.toList());
                databaseList.addAll(collect);
            } else {
                databaseList.clear();
                for (Result movieModelo : databaseListFull) {
                    if (movieModelo.getTitle().toLowerCase().contains(search)) {
                        databaseList.add(movieModelo);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;

        public View view;

        public ViewHolder(View view) {
            super(view);

            this.view = view;
            this.imageView = view.findViewById(R.id.imageMovie);
        }
    }
}
