package com.jomibusa.themoviedb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jomibusa.themoviedb.R;
import com.jomibusa.themoviedb.modelo.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    private int resource;
    private Context context;
    private ArrayList<Movie> databaseList;
    private List<Movie> databaseListFull;
    private int lastPosition = -1;

    public MovieAdapter(ArrayList<Movie> databaseList, int resource, Context context) {
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

        final Movie database = databaseList.get(position);

        //holder.textViewPalabra.setText(database.getClass());

        //holder.container.setAnimation(AnimationUtils.loadAnimation(context, R.anim.animacion_recycler));

        try {
            Picasso.get().load("").fit().into(holder.imageView);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        holder.view.setOnClickListener(view -> {

        });

    }

    @Override
    public int getItemCount() {
        return databaseList.size();
    }

    /*public void filter(final String search) {
        if (search.length() == 0) {
            databaseList.clear();
            databaseList.addAll(databaseListFull);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                databaseList.clear();
                List<Movie> collect = databaseListFull.stream()
                        .filter(i -> i.getNombre().toLowerCase().contains(search))
                        .collect(Collectors.toList());
                databaseList.addAll(collect);
            } else {
                databaseList.clear();
                for (Movie movieModelo : databaseListFull) {
                    if (movieModelo.getNombre().toLowerCase().contains(search)) {
                        databaseList.add(movieModelo);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }*/


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
