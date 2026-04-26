package com.example.stars.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.stars.R;
import com.example.stars.classes.Star;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.List;

public class StarAdapter extends RecyclerView.Adapter<StarAdapter.StarViewHolder> {

    private List<Star> stars;
    private Context context;

    public StarAdapter(Context context, List<Star> stars) {
        this.context = context;
        this.stars = stars;
    }

    public void updateList(List<Star> newList) {
        this.stars = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_star, parent, false);
        return new StarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StarViewHolder holder, int position) {
        Star star = stars.get(position);
        holder.nom.setText(star.getNom().toUpperCase());
        holder.ratingBar.setRating(star.getRating());
        Glide.with(context).load(star.getImageRes()).into(holder.image);

        holder.itemView.setOnClickListener(v -> {
            View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_rating, null);
            RatingBar rb = dialogView.findViewById(R.id.dialogRatingBar);
            rb.setRating(star.getRating());

            new AlertDialog.Builder(context)
                    .setTitle(star.getNom())
                    .setView(dialogView)
                    .setPositiveButton("OK", (dialog, which) -> {
                        star.setRating(rb.getRating());
                        notifyItemChanged(holder.getAdapterPosition());
                    })
                    .setNegativeButton("Annuler", null)
                    .show();
        });
    }

    @Override
    public int getItemCount() { return stars.size(); }

    static class StarViewHolder extends RecyclerView.ViewHolder {
        CircleImageView image;
        TextView nom;
        RatingBar ratingBar;

        StarViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.starImage);
            nom = itemView.findViewById(R.id.starNom);
            ratingBar = itemView.findViewById(R.id.starRating);
        }
    }
}