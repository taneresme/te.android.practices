package com.android.taner.hw05.movie;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.taner.hw05.R;

public class MovieViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;
    private TextView textViewMovieName;
    private TextView textViewMovieYear;

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);

        this.imageView = itemView.findViewById(R.id.imageView);
        this.textViewMovieName = itemView.findViewById(R.id.txtMovieName);
        this.textViewMovieYear = itemView.findViewById(R.id.txtMovieYear);
    }

    void bind(int image, String movieName, Integer movieYear){
        this.imageView.setImageResource(image);
        this.textViewMovieName.setText(movieName);
        this.textViewMovieYear.setText(movieYear.toString());
    }
}
