package com.example.movieapp.view;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    ImageView image;
    TextView title;
    TextView year;
    TextView director;

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);

        image = itemView.findViewById(R.id.movieImage);
        title = itemView.findViewById(R.id.textTitle);
        year = itemView.findViewById(R.id.textYear);
        director = itemView.findViewById(R.id.textDirector);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Click", "OnClick: ");
            }
        });
    }
}
